# Strassen algorithem
## Goal:
Strassen algorithm for multiplaying matrices. The algorithem time complexity is better then that of normal n^3 multiplying.
## Input:
is type of 
`n` `m`
followed by two `n` by `n` matricies.

n - size of one matrix
m - size of a matrix at which the algorithm should just use normal n^3 multiplication.

## Output:
submatricies and at the end multiplied matrix.

Formulas:

```
	m1 = (a11+a22)(b11+b22)
	m2 = (a21+a22)b11
	m3 = a11(b12-b22)
	m4 = a22(b21-b11)
	m5 = (a11+a12)b22
	m6 = (a21-a11)(b11+b12)
	m7 = (a12-a22)(b21+b22)
```
```
    c11 =  m1+m4-m5+m7
	c12 = m3+m5
	c21 = m2+m4
	c22 = m1-m2+m3+m6
```