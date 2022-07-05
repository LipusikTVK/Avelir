from requests import get
import os
import subprocess

# Конфигурация настройки сервера.
# Версия ядра, которая необходима для загрузки.
VERSION = "1.17.1"

# Имя файла ядра сервера.
CORE_NAME = "core.jar"

# Ссылка, по которому будет запрос на скачивание ядра
# {} - аргумент версии
CORE_URL = "https://download.getbukkit.org/spigot/spigot-{}.jar"

# Какую программу для запуска использовать.
JAVA = "/usr/lib/jvm/java-17-openjdk/bin/java"

# Настройки запуска ядра
# Активирует серверную версию Java, в которой по умолчанию включена 
# функция поддержки экспериментальных флагов, а также ускоряет 
# компиляцию классов, что даёт прирост в производительности, 
# но увеличивает время запуска (только 64-битные системы).
JAVA_SERVER_VERSION = True

# Колличество максимальной (MAX) и минимальной (MIN) 
# выделяемой памяти под сервер в мегабайтах
JAVA_MAX_MEMORY = 4012
JAVA_MIN_MEMORY = 512

# Колличество выделяемой памяти под недолгоживущие объекты
# Указывается в мегабайтах
JAVA_MAX_UNLOAD_MEMORY = 128

# Тип сборщка мусора в зависимости от использования потоков
# Нуль или один - использовать однопоточный сборщик
# Больше одного - использовать несколько потоков. 
JAVA_GC_TYPE = 2

# Максимальное время блокирования сборщика между сборками.
# Указывается в миллисекундах.
JAVA_MAX_GC_PAUSED = 200

# Нужно-ли использовать сборщик мусора, который разбивает
# память на учатски для очистки.
JAVA_USE_NEW_GC = True

# Если используется новый сборщик, то этот аргумент отвечает
# за длину такого участка в мегабайтах.
JAVA_HEAP_MEMORY = 32

# Функция скачивания ядра.
def core_download():
    if not os.path.exists(CORE_NAME):
        print("Загружаем ядро...")
        with open(CORE_NAME, "wb") as file:
            response = get(CORE_URL.format(VERSION))
            file.write(response.content)
            print("Ядро загружёно!")
    else:
        print("Ядро есть.")
        
# Функция проверки и скачивания зависимостей.
def download_dependencies():
    core_download()
    
    if not os.path.exists("plugins"):
        os.mkdir("plugins")
        
    print("Получаем плагины...")
    
    with open("pluginlist", "r") as file:
        for line in file.read().split('\n'):
            arr = line.split()
            if len(arr) == 0:
                continue
                
            name = "plugins/" + arr[0] + ".jar"
            
            if not os.path.exists(name):
                print("Загружаем " + name + "...")
                with open(name, "wb") as file2:
                    response = get(arr[1])
                    file2.write(response.content)
                    print(name + " загружён!")
            else:
                print(name + " уже есть.")
                
def run_core():
    print("Запускаем ядро...")

    arguments = [JAVA]
    
    if JAVA_SERVER_VERSION == True:
        arguments = arguments + ["-server"]
        
    arguments = arguments + ["-Xmx" + str(JAVA_MAX_MEMORY) + "M"]
    arguments = arguments + ["-Xms" + str(JAVA_MIN_MEMORY) + "M"]
    arguments = arguments + ["-Xmn" + str(JAVA_MAX_UNLOAD_MEMORY) + "M"]
    
    if JAVA_GC_TYPE == 0 or JAVA_GC_TYPE == 1:
        arguments = arguments + ["-XX:+UseSerialGC"]
    else:
        if JAVA_USE_NEW_GC == True:
            arguments = arguments + ["-XX:+UseG1GC"]
            arguments = arguments + ["-XX:+ParallelRefProcEnabled"]
            arguments = arguments + ["-XX:G1HeapRegionSize=" + str(JAVA_HEAP_MEMORY)]
        else:
            arguments = arguments + ["-XX:+ParallelRefProcEnabled"]

    arguments = arguments + ["-XX:MaxGCPauseMillis=" + str(JAVA_MAX_GC_PAUSED)]
        
    arguments = arguments + ["-jar", CORE_NAME, "nogui"]
    arguments = ' '.join(arguments)
        
    print(arguments)
        
    core_handle = subprocess.Popen(arguments, shell = True)
    core_handle.wait()
                    
download_dependencies()
run_core()
