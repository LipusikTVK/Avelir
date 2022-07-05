/*
 * @(#)Statistics.java        5.0.4    2022-05-22
 *
 * MathParser.org-mXparser DUAL LICENSE AGREEMENT as of date 2022-05-22
 * The most up-to-date license is available at the below link:
 * - https://mathparser.org/mxparser-license
 *
 * AUTHOR: Copyright 2010 - 2022 Mariusz Gromada - All rights reserved
 * PUBLISHER: INFIMA - https://payhip.com/infima
 *
 * SOFTWARE means source code and/or binary form and/or documentation.
 * PRODUCT: MathParser.org-mXparser SOFTWARE
 * LICENSE: DUAL LICENSE AGREEMENT
 *
 * BY INSTALLING, COPYING, OR OTHERWISE USING THE PRODUCT, YOU AGREE TO BE
 * BOUND BY ALL OF THE TERMS AND CONDITIONS OF THE DUAL LICENSE AGREEMENT.
 *
 * AUTHOR & PUBLISHER provide the PRODUCT under the DUAL LICENSE AGREEMENT
 * model designed to meet the needs of both non-commercial use as well as
 * commercial use.
 *
 * NON-COMMERCIAL USE means any use or activity where a fee is not charged
 * and the purpose is not the sale of a good or service, and the use or
 * activity is not intended to produce a profit. NON-COMMERCIAL USE examples:
 *
 * 1. Free Open-Source Software ("FOSS").
 * 2. Non-commercial use in research, scholarly and education.
 *
 * COMMERCIAL USE means any use or activity where a fee is charged or the
 * purpose is the sale of a good or service, or the use or activity is
 * intended to produce a profit. COMMERCIAL USE examples:
 *
 * 1. OEMs (Original Equipment Manufacturers).
 * 2. ISVs (Independent Software Vendors).
 * 3. VARs (Value Added Resellers).
 * 4. Other distributors that combine and distribute commercially licensed
 *    software.
 *
 * IN CASE YOU WANT TO USE THE PRODUCT COMMERCIALLY, YOU MUST PURCHASE THE
 * APPROPRIATE LICENSE FROM "INFIMA" ONLINE STORE, STORE ADDRESS:
 *
 * 1. https://mathparser.org/order-commercial-license
 * 2. https://payhip.com/infima
 *
 * NON-COMMERCIAL LICENSE
 *
 * Redistribution and use of the PRODUCT in source and/or binary forms,
 * with or without modification, are permitted provided that the following
 * conditions are met:
 *
 * 1. Redistributions of source code must retain unmodified content of the
 *    entire MathParser.org-mXparser DUAL LICENSE AGREEMENT, including
 *    definition of NON-COMMERCIAL USE, definition of COMMERCIAL USE,
 *    NON-COMMERCIAL LICENSE conditions, COMMERCIAL LICENSE conditions, and
 *    the following DISCLAIMER.
 * 2. Redistributions in binary form must reproduce the entire content of
 *    MathParser.org-mXparser DUAL LICENSE AGREEMENT in the documentation
 *    and/or other materials provided with the distribution, including
 *    definition of NON-COMMERCIAL USE, definition of COMMERCIAL USE,
 *    NON-COMMERCIAL LICENSE conditions, COMMERCIAL LICENSE conditions, and
 *    the following DISCLAIMER.
 * 3. Any form of redistribution requires confirmation and signature of
 *    the NON-COMMERCIAL USE by successfully calling the method:
 *       License.iConfirmNonCommercialUse(...)
 *    The method call takes place only internally for logging purposes and
 *    there is no connection with other external services and no data is
 *    sent or collected. The lack of a method call (or its successful call)
 *    does not affect the operation of the PRODUCT in any way. Please see
 *    the API documentation.
 *
 * COMMERCIAL LICENSE
 *
 *  1. Before purchasing a commercial license, AUTHOR & PUBLISHER allow you
 *     to download, install and use up to three copies of the PRODUCT to
 *     perform integration tests, confirm the quality of the PRODUCT and
 *     its suitability. The testing period should be limited to fourteen
 *     days. Tests should be performed under the conditions of test
 *     environments. The purpose of the tests must not be to generate profit.
 *  2. Provided that you purchased a license from "INFIMA" online store
 *     (store address: https://mathparser.org/order-commercial-license or
 *     https://payhip.com/infima), and you comply with all below terms and
 *     conditions, and you have acknowledged and understood the following
 *     DISCLAIMER, AUTHOR & PUBLISHER grant you a nonexclusive license
 *     including the following rights:
 *  3. The license has been granted only to you, i.e., the person or entity
 *     that made the purchase, who is identified and confirmed by the data
 *     provided during the purchase.
 *  4. In case you purchased a license in the "ONE-TIME PURCHASE" model,
 *     the license has been granted only for the PRODUCT version specified
 *     in the purchase. The upgrade policy gives you additional rights and
 *     is described in the dedicated section below.
 *  5. In case you purchased a license in the "SUBSCRIPTION" model, you can
 *     install and use any version of the PRODUCT, but only during the
 *     subscription validity period.
 *  6. In case you purchased a "SINGLE LICENSE" you can install and use the
 *     PRODUCT from one workstation.
 *  7. Additional copies of the PRODUCT can be installed and used from more
 *     than one workstation; however, this number is limited to the number
 *     of workstations purchased as per order.
 *  8. In case you purchased a "SITE LICENSE ", the PRODUCT can be installed
 *     and used from all workstations located at your premises.
 *  9. You may incorporate the unmodified PRODUCT into your own products
 *     and software.
 * 10. If you purchased a license with the "SOURCE CODE" option, you may
 *     modify the PRODUCT's source code and incorporate the modified source
 *     code into your own products and/or software.
 * 11. Provided that the license validity period has not expired, you may
 *     distribute your product and/or software with the incorporated
 *     PRODUCT royalty-free.
 * 12. You may make copies of the PRODUCT for backup and archival purposes.
 * 13. Any form of redistribution requires confirmation and signature of
 *     the COMMERCIAL USE by successfully calling the method:
 *        License.iConfirmCommercialUse(...)
 *     The method call takes place only internally for logging purposes and
 *     there is no connection with other external services and no data is
 *     sent or collected. The lack of a method call (or its successful call)
 *     does not affect the operation of the PRODUCT in any way. Please see
 *     the API documentation.
 * 14. AUTHOR & PUBLISHER reserve all rights not expressly granted to you
 *     in this agreement.
 *
 * ADDITIONAL CLARIFICATION ON WORKSTATION
 *
 * A workstation is a device, a remote device, or a virtual device, used by
 * you, your employees, or other entities to whom you have commissioned the
 * tasks. For example, the number of workstations may refer to the number
 * of software developers, engineers, architects, scientists, and other
 * professionals who use the PRODUCT on your behalf. The number of
 * workstations is not the number of copies of your end-product that you
 * distribute to your end-users.
 *
 * By purchasing the COMMERCIAL LICENSE, you only pay for the number of
 * workstations, while the number of copies of your final product
 * (delivered to your end-users) is not limited.
 *
 * UPGRADE POLICY
 *
 * The PRODUCT is versioned according to the following convention:
 *
 *    [MAJOR].[MINOR].[PATCH]
 *
 * 1. COMMERCIAL LICENSE holders can install and use the updated version
 *    for bug fixes free of charge, i.e. if you have purchased a license
 *    for the [MAJOR].[MINOR] version (e.g.: 5.0), you can freely install
 *    all the various releases specified in the [PATCH] version (e.g.: 5.0.2).
 *    The license terms remain unchanged after the update.
 * 2. COMMERCIAL LICENSE holders for [MAJOR].[MINOR] version (e.g.: 5.0)
 *    can install and use the updated version [MAJOR].[MINOR + 1] free of
 *    charge, i.e., plus one release in the [MINOR] range (e.g.: 5.1). The
 *    license terms remain unchanged after the update.
 * 3. COMMERCIAL LICENSE holders who wish to upgrade their version, but are
 *    not eligible for the free upgrade, can claim a discount when
 *    purchasing the upgrade. For this purpose, please contact us via e-mail.
 *
 * DISCLAIMER
 *
 * THIS PRODUCT IS PROVIDED BY AUTHOR & PUBLISHER "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL AUTHOR OR PUBLISHER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS PRODUCT, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 *
 * THE VIEWS AND CONCLUSIONS CONTAINED IN THE PRODUCT AND DOCUMENTATION ARE
 * THOSE OF THE AUTHORS AND SHOULD NOT BE INTERPRETED AS REPRESENTING
 * OFFICIAL POLICIES, EITHER EXPRESSED OR IMPLIED, OF AUTHOR OR PUBLISHER.
 *
 * CONTACT
 *
 * - e-mail: info@mathparser.org
 * - website: https://mathparser.org
 * - source code: https://github.com/mariuszgromada/MathParser.org-mXparser
 * - online store: https://mathparser.org/order-commercial-license
 * - online store: https://payhip.com/infima
 */
package org.mariuszgromada.math.mxparser.mathcollection;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;

/**
 * Statistics - i.e.: mean, variance, standard deviation, etc.
 *
 * @author         <b>Mariusz Gromada</b><br>
 *                 <a href="https://mathparser.org" target="_blank">MathParser.org - mXparser project page</a><br>
 *                 <a href="https://github.com/mariuszgromada/MathParser.org-mXparser" target="_blank">mXparser on GitHub</a><br>
 *                 <a href="https://payhip.com/INFIMA" target="_blank">INFIMA place to purchase a commercial MathParser.org-mXparser software license</a><br>
 *                 <a href="mailto:info@mathparser.org">info@mathparser.org</a><br>
 *                 <a href="https://scalarmath.org/" target="_blank">ScalarMath.org - a powerful math engine and math scripting language</a><br>
 *                 <a href="https://play.google.com/store/apps/details?id=org.mathparser.scalar.lite" target="_blank">Scalar Lite</a><br>
 *                 <a href="https://play.google.com/store/apps/details?id=org.mathparser.scalar.pro" target="_blank">Scalar Pro</a><br>
 *                 <a href="https://mathspace.pl" target="_blank">MathSpace.pl</a><br>
 *
 * @version        5.0.0
 */
public final class Statistics {
	/**
	 * Average from sample function values - iterative operator.
	 *
	 * @param      f                   the expression
	 * @param      index               the name of index argument
	 * @param      from                FROM index = form
	 * @param      to                  TO index = to
	 * @param      delta               BY delta
	 *
	 * @return     product operation (for empty product operations returns 1).
	 *
	 * @see        Expression
	 * @see        Argument
	 */
	public static double avg(Expression f, Argument index, double from, double to, double delta) {
		if ( (Double.isNaN(delta) ) || (Double.isNaN(from) ) || (Double.isNaN(to) ) || (delta == 0) )
			return Double.NaN;
		double sum = 0;
		int n = 0;
		if ( (to >= from) && (delta > 0) ) {
			double i;
			for (i = from; i < to; i+=delta) {
				if (mXparser.isCurrentCalculationCancelled()) return Double.NaN;
				sum += mXparser.getFunctionValue(f, index, i);
				n++;
			}
			if ( delta - (i - to) > 0.5 * delta) {
				if (mXparser.isCurrentCalculationCancelled()) return Double.NaN;
				sum += mXparser.getFunctionValue(f, index, to);
				n++;
			}
		} else if ( (to <= from) && (delta < 0) ) {
			double i;
			for (i = from; i > to; i+=delta) {
				if (mXparser.isCurrentCalculationCancelled()) return Double.NaN;
				sum += mXparser.getFunctionValue(f, index, i);
				n++;
			}
			if ( -delta - (to - i) > -0.5 * delta) {
				if (mXparser.isCurrentCalculationCancelled()) return Double.NaN;
				sum += mXparser.getFunctionValue(f, index, to);
				n++;
			}
		} else if (from == to)
			return mXparser.getFunctionValue(f, index, from);
		return sum / n;
	}
	/**
	 * Bias-corrected variance from sample function values - iterative operator.
	 *
	 * @param      f                   the expression
	 * @param      index               the name of index argument
	 * @param      from                FROM index = form
	 * @param      to                  TO index = to
	 * @param      delta               BY delta
	 *
	 * @return     product operation (for empty product operations returns 1).
	 *
	 * @see        Expression
	 * @see        Argument
	 */
	public static double var(Expression f, Argument index, double from, double to, double delta) {
		if ( (Double.isNaN(delta) ) || (Double.isNaN(from) ) || (Double.isNaN(to) ) || (delta == 0) )
			return Double.NaN;
		return var( mXparser.getFunctionValues(f, index, from, to, delta) );
	}
	/**
	 * Bias-corrected standard deviation from sample function values - iterative operator.
	 *
	 * @param      f                   the expression
	 * @param      index               the name of index argument
	 * @param      from                FROM index = form
	 * @param      to                  TO index = to
	 * @param      delta               BY delta
	 *
	 * @return     product operation (for empty product operations returns 1).
	 *
	 * @see        Expression
	 * @see        Argument
	 */
	public static final double std(Expression f, Argument index, double from, double to, double delta) {
		if ( (Double.isNaN(delta) ) || (Double.isNaN(from) ) || (Double.isNaN(to) ) || (delta == 0) )
			return Double.NaN;
		return std( mXparser.getFunctionValues(f, index, from, to, delta) );
	}
	/**
	 * Sample average.
	 *
	 * @param      numbers             the numbers
	 *
	 * @return     if each number from numbers &lt;&gt; Double.NaN returns
	 *             avg(a_1,...,a_n) a_1,...,a_n in numbers,
	 *             otherwise returns Double.NaN.
	 */
	public static double avg(double... numbers) {
		if (numbers == null) return Double.NaN;
		if (numbers.length == 0) return Double.NaN;
		if (numbers.length == 1) return numbers[0];
		double sum = 0;
		for (double xi : numbers) {
			if (mXparser.isCurrentCalculationCancelled()) return Double.NaN;
			if ( Double.isNaN(xi) )
				return Double.NaN;
			sum+=xi;
		}
		return sum / numbers.length;
	}
	/**
	 * Sample variance (biased-corrected).
	 *
	 * @param      numbers             the numbers
	 *
	 * @return     if each number from numbers &lt;&gt; Double.NaN returns
	 *             Var(a_1,...,a_n) a_1,...,a_n in numbers,
	 *             otherwise returns Double.NaN.
	 */
	public static double var(double... numbers) {
		if (numbers == null) return Double.NaN;
		if (numbers.length == 0) return Double.NaN;
		if (numbers.length == 1) {
			if (Double.isNaN(numbers[0])) return Double.NaN;
			return 0;
		}
		double m = avg(numbers);
		double sum = 0;
		for (double xi : numbers) {
			if (mXparser.isCurrentCalculationCancelled()) return Double.NaN;
			if ( Double.isNaN(xi) )
				return Double.NaN;
			sum+=(xi-m)*(xi-m);
		}
		return sum / (numbers.length - 1);
	}
	/**
	 * Sample standard deviation (biased-corrected).
	 *
	 * @param      numbers             the numbers
	 *
	 * @return     if each number from numbers &lt;&gt; Double.NaN returns
	 *             Std(a_1,...,a_n) a_1,...,a_n in numbers,
	 *             otherwise returns Double.NaN.
	 */
	public static double std(double... numbers) {
		if (numbers == null) return Double.NaN;
		if (numbers.length == 0) return Double.NaN;
		if (numbers.length == 1) {
			if (Double.isNaN(numbers[0])) return Double.NaN;
			return 0;
		}
		return MathFunctions.sqrt( var(numbers) );
	}
	/**
	 * Sample median
	 * @param numbers   List of number
	 * @return          Sample median, if table was empty or null then Double.NaN is returned.
	 */
	public static double median(double... numbers) {
		if (numbers == null) return Double.NaN;
		if (numbers.length == 0) return Double.NaN;
		if (numbers.length == 1) return numbers[0];
		if (numbers.length == 2) return (numbers[0] + numbers[1]) / 2.0;
		for (double v : numbers) {
			if (mXparser.isCurrentCalculationCancelled()) return Double.NaN;
			if (Double.isNaN(v)) return Double.NaN;
		}
		NumberTheory.sortAsc(numbers);
		if ((numbers.length % 2) == 1) {
			int i = (numbers.length-1) / 2;
			return numbers[i];
		} else {
			int i = ( numbers.length / 2 ) - 1;
			return (numbers[i] + numbers[i+1]) / 2.0;
		}
	}
	/**
	 * Sample mode
	 * @param numbers   List of number
	 * @return          Sample median, if table was empty or null then Double.NaN is returned.
	 */
	public static double mode(double... numbers) {
		if (numbers == null) return Double.NaN;
		if (numbers.length == 0) return Double.NaN;
		if (numbers.length == 1) return numbers[0];
		for (double v : numbers) {
			if (mXparser.isCurrentCalculationCancelled()) return Double.NaN;
			if (Double.isNaN(v)) return Double.NaN;
		}
		double[][] dist = NumberTheory.getDistValues(numbers, true);
		return dist[0][0];
	}
}