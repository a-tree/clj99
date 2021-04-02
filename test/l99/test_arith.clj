(ns l99.test-arith
  (:require [clojure.test :refer :all]
            [l99.arith :as arith]
            [l99.list :as list]))

(deftest test-p31
  "Determine whether a given integer number is prime."
  (testing "p31"
    (is (= true (arith/l99-is-prime 7)))
    (is (= false (arith/l99-is-prime 10)))))

(deftest test-p32
  "Determine the greatest common divisor of two positive integer numbers.
Use Euclid's algorithm."
  (testing "p32"
    (is (= 9 (arith/l99-gcd 36 63)))))

(deftest test-p33
  "Determine whether two positive integer numbers are coprime.
Two numbers are coprime if their greatest common divisor equals 1."
  (is (= true (arith/l99-coprime 35 64)))
  (is (= false (arith/l99-coprime 32 64))))

(deftest test-p34
  "Calculate Euler's totient function phi(m).
Euler's so-called totient function phi(m) is defined as the number of positive integers r (1 <= r < m) that are coprime to m.
Example: m = 10: r = 1,3,7,9; thus phi(m) = 4. Note the special case: phi(1) = 1.
* (totient-phi 10)
4
;; Find out what the value of phi(m) is if m is a prime number. Euler's totient function plays an important role in one of the most widely used public key cryptography methods (RSA). In this exercise you should use the most primitive method to calculate this function (there are smarter ways that we shall discuss later)."
  (is (= 4 (arith/l99-totient-phi 10)))
  ;; https://ja.wikipedia.org/wiki/%E3%82%AA%E3%82%A4%E3%83%A9%E3%83%BC%E3%81%AE%CF%86%E9%96%A2%E6%95%B0
  ;; phi(1) ... phi(20)
  (is (= '(1, 1, 2, 2, 4, 2, 6, 4, 6, 4, 10, 4, 12, 6, 8, 8, 16, 6, 18, 8)
         (map arith/l99-totient-phi (list/l99-range 1 20)))))

(deftest test-p35
  "Determine the prime factors of a given positive integer.
Construct a flat list containing the prime factors in ascending order."
  (is (= '(3 3 5 7) (arith/l99-prime-factors 315)))
  (is (= '(7 13) (arith/l99-prime-factors 91))))

(deftest test-p36
  "Determine the prime factors of a given positive integer (2).
Construct a list containing the prime factors and their multiplicity.
Example:
* (prime-factors-mult 315)
((3 2) (5 1) (7 1))"
  (is (= '((3 2) (5 1) (7 1)) (arith/l99-prime-factors-mult 315))))

(deftest test-p37
  "Calculate Euler's totient function phi(m) (improved).
See problem P34 for the definition of Euler's totient function. If the list of the prime factors of a number m is known in the form of problem P36 then the function phi(m) can be efficiently calculated as follows: Let ((p1 m1) (p2 m2) (p3 m3) ...) be the list of prime factors (and their multiplicities) of a given number m. Then phi(m) can be calculated with the following formula:
phi(m) = (p1 - 1) * p1 ** (m1 - 1) + (p2 - 1) * p2 ** (m2 - 1) + (p3 - 1) * p3 ** (m3 - 1) + ... 
Note that a ** b stands for the b'th power of a."
  (is (= 4 (arith/l99-improved-totient-phi 10))))

;; (deftest test-p38
;;   "Compare the two methods of calculating Euler's totient function.
;; Use the solutions of problems P34 and P37 to compare the algorithms. Take the number of logical inferences as a measure for efficiency. Try to calculate phi(10090) as an example."
;;   )

(deftest test-p39
  "A list of prime numbers.
Given a range of integers by its lower and upper limit, construct a list of all prime numbers in that range."
  (is (= '(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
         (filter arith/l99-is-prime (list/l99-range 2 100)))))

(deftest test-p40
  "Goldbach's conjecture.
Goldbach's conjecture says that every positive even number greater than 2 is the sum of two prime numbers. Example: 28 = 5 + 23. It is one of the most famous facts in number theory that has not been proved to be correct in the general case. It has been numerically confirmed up to very large numbers (much larger than we can go with our Prolog system). Write a predicate to find the two prime numbers that sum up to a given even integer."
  (is (= '(5 23) (arith/first-goldbach 28))))

(deftest test-p41-1
  "Given a range of integers by its lower and upper limit, print a list of all even numbers and their Goldbach composition."
  (is (= '((10 3 7) (12 5 7) (14 3 11) (16 3 13) (18 5 13) (20 3 17))
         (arith/l99-goldbach-list 9 20))))

(deftest test-p41-2
  "In most cases, if an even number is written as the sum of two prime numbers, one of them is very small. Very rarely, the primes are both bigger than say 50. Try to find out how many such cases there are in the range 2..3000."
  (is (= '((992 73 919)) (arith/l99-goldbach-list 992 992 :min 50))))
