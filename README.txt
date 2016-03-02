Authur: Yan Zhen Lin
project title: DataPlot

The goal of this project is to predict mathematical models of two variable systems base on pair wise cartesian coordinate plot. Once the mathematical model is generated, we can use it predict future results base on any given dependent value. This manual will go over the theoretical ideas, the implementation will not be explained.
Given n initial cartesian data points, we want to know what is the closest corresponding correlation between the dependent x-values and the corresponding Y-value. (sometimes, there is no correlation!) The general polynomial equation is characterized by the following equation: Y = a_0 + a_1*X + ……… a_k*X^K. The sum of squared residual of the equation is then define by  R^2 = ∑ [y-(a_0 + a_1*X + ……… a_k*X^K)]^2, from 1 …. n, where K is the number of degree polynomials we have. The goal for us is to find the the equation of the Kth degree polynomial where the squared residual S is minimized. In order to achieve this, we will generate the partial derivative of R^2 with respect to a_0, a_1, …..a_k , and set each partial equation to 0, since we are trying to minimize the sum of residuals. 
this is part we will use http://mathworld.wolfram.com/ notes to explain.

The partial derivatives (again dropping superscripts) are

(partial(R^2))/(partiala_0)	=	-2sum_(i=1)^(n)[y-(a_0+a_1x+...+a_kx^k)]=0	
(3)
(partial(R^2))/(partiala_1)	=	-2sum_(i=1)^(n)[y-(a_0+a_1x+...+a_kx^k)]x=0	
(4)
(partial(R^2))/(partiala_k)	=	-2sum_(i=1)^(n)[y-(a_0+a_1x+...+a_kx^k)]x^k=0.

These lead to the equations

a_0n+a_1sum_(i=1)^(n)x_i+...+a_ksum_(i=1)^(n)x_i^k	=	sum_(i=1)^(n)y_i	
(6)
a_0sum_(i=1)^(n)x_i+a_1sum_(i=1)^(n)x_i^2+...+a_ksum_(i=1)^(n)x_i^(k+1)	=	sum_(i=1)^(n)x_iy_i	
(7)
a_0sum_(i=1)^(n)x_i^k+a_1sum_(i=1)^(n)x_i^(k+1)+...+a_ksum_(i=1)^(n)x_i^(2k)	=	sum_(i=1)^(n)x_i^ky_i

or, in matrix form

 [n sum_(i=1)^(n)x_i ... sum_(i=1)^(n)x_i^k; sum_(i=1)^(n)x_i sum_(i=1)^(n)x_i^2 ... sum_(i=1)^(n)x_i^(k+1); | | ... |; sum_(i=1)^(n)x_i^k sum_(i=1)^(n)x_i^(k+1) ... sum_(i=1)^(n)x_i^(2k)][a_0; a_1; |; a_k]=[sum_(i=1)^(n)y_i; sum_(i=1)^(n)x_iy_i; |; sum_(i=1)^(n)x_i^ky_i].  

Now our general problem becomes XA = Y, where A is the column vector for the coefficients of a_0, a_1…… a_k which we need to solve. In order to solve this equation, we will need to perform pre-multiplication of the inverse matrix of X, such that we have (X^-1 X)A = X^-1 Y which equates to A = (X^-1)Y. As long as X^-1 does not equal the zero matrix, this solution is valid. Once we figure out the A column vector, we simply just plugin our values into our k-degree polynomial model: Y = a_0 + a_1*X + ……… a_k*X^K. 
Now, how do we know which k-degree polynomial is the best? We won't know truly until we run a assessment fit test. There are two main quantities that we will focus our attention on, namely, the total sum of squares SSTo, and the residual sum of squares. 

SSTO = (y_1 - Y_avg)^2 + (y_2 - Y_avg)^2 + …+ (y_n - Y_avg)^2

SSResid = (y_1-y_1_predicted)^2+(y_2-y_2_predicted)^2+…..+(y_n-y_n_predicted)^2

Where the coefficient of determination is given by:

R^2 = 1- SSResid/SSTo. this is proportion of the data that can be characterized by the kth degree polynomial correlation between x and y. The highest value of R^2 will indicate that for whichever k degree, that model has the highest accuracy of prediction for the data in the range of data provided.