
##Usage: 
``
  java -jar n-gram_pro.jar N input.fasta output.libsvm method label
  Example: java -jar n-gram_pro.jar 2 input.fasta output.libsvm 1 1
``

where ``N`` is N-gram param; ``method`` is 1 or 2; ``label`` is your libsvm label, such as 1 or -1

##Note: 

1. feature dimension of your libsvm file is sum(20^(N)). For example, N=2, feature is 420; N=3, feature is 8420.
2. if feature value is 0, 0 won't be displayed.
3. Contact: Shixiang Wan, shixiangwan@foxmail.com

##Update:
2016.10.23: bug fix