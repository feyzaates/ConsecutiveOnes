The project includes the consecutive ones problem coded using the genetic algorithm method. 
The aim is to treat 10-digit binary numbers as individuals in different experimental environments and increase the count of consecutive ones using various genetic programming methods.


	                                      Problem 1	   Problem 2   	  Problem 3	  Problem 4
     n	                                10	       100	     100	     100
    initial population size (m)	        10	       100	     100	     1000
    number of iterations	                10             100	     1000          100
    crossover type	                                   One Point Crossover
    crossover ratio	                                             70%
    mutation type	                          bitflip (flipping n / 3 bits at random)
    mutation ratio                                   	             30%
    mating selection                            	       roulette wheel 
                                                 elitism (transferring m /10 individuals) 
    survival selection                                                 + 
                                            roulette wheel  (for transferring 9*m / 10 individuals)
