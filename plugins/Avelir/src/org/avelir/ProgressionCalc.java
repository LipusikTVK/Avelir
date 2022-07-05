package org.avelir;

import java.time.Duration;

import org.mariuszgromada.math.mxparser.Constant;
import org.mariuszgromada.math.mxparser.Expression;

public class ProgressionCalc
{
	double first_member;
	String type; // algebraic, geometric, custom
	String formule;
	
	public ProgressionCalc(double first_member, String type, String formule)
	{
		this.first_member = first_member;
		this.type = type;
		this.formule = formule;
	}
	
	public double take(int count)
	{
		double dumb_first = first_member;
		
		for (int i = 0; i < count; i++)
		{
			Constant a = new Constant("a", dumb_first);
			Constant b = new Constant("b", first_member);
			Constant ie = new Constant("i", i);
			
			Expression expression = new Expression(formule, a, b, ie);
			dumb_first += expression.calculate();
		}
		
		return dumb_first;
	}
}
