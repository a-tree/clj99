(ns l99.test-list
  (:require [clojure.test :refer :all]
            [l99.list :as list]))

(deftest test-p01
  "Find the last box of a list.
Example:
* (l99-last '(a b c d))
(D)"
  (testing "p01"
    (is (= '(d) (list/l99-last '(a b c d))))))

(deftest test-p02
  "Find the last but one box of a list.
Example:
* (l99-but-last '(a b c d))
(C D)
"
  (testing "p02"
    (is (= '(c d) (list/l99-but-last '(a b c d))))))

(deftest test-p03
    "Find the K'th element of a list.
The first element in the list is number 1.
Example:
* (l99-element-at '(a b c d e) 3)
C"
  (testing "p03"
    (is (= 'c (list/l99-element-at '(a b c d e) 3)))))

(deftest test-p04
  "Find the number of elements of a list."
  (testing "p04"
        (is (= 4 (list/l99-length '(a b c d))))))

(deftest test-p05
  "Reverse a list."
  (testing "p05"
    (is (= '(e d c b a) (list/l99-reverse '(a b c d e))))))

(deftest test-p06
  "Find out whether a list is a palindrome.
A palindrome can be read forward or backward; e.g. (x a m a x)."
  (testing "p06"
    (is (= true (list/l99-palindrome? '(x a m a x))))
    (is (= false (list/l99-palindrome? '(a b c d e))))))

(deftest test-p07
  "Flatten a nested list structure.
1Transform a list, possibly holding lists as elements into a `flat' list by replacing each list with its elements (recursively)."
  (testing "p07"
    (is (= '(a b c d e)
           (list/l99-flatten-list '(a (b (c d) e)))))))

(deftest test-p08
  "Eliminate consecutive duplicates of list elements.
If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed."
  (testing "p08"
    (is (= '(a b c a d e)
           (list/l99-compress '(a a a a b c c a a d e e e e))))))

(deftest test-p09
  "Pack consecutive duplicates of list elements into sublists.
If a list contains repeated elements they should be placed in separate sublists."
  (testing "p09"
    (is (= '((a a a a) (b) (c c) (a a) (d) (e e e e))
           (list/l99-pack '(a a a a b c c a a d e e e e))))))

(deftest test-p10
  "Run-length encoding of a list.
Use the result of problem P09 to implement the so-called run-length encoding data compression method. Consecutive duplicates of elements are encoded as lists (N E) where N is the number of duplicates of the element E."
  (testing "p10"
    (is (= '((4 a) (1 b) (2 c) (2 a) (1 d)(4 e))
           (list/l99-encode '(a a a a b c c a a d e e e e))))))

(deftest test-p11
  "Modified run-length encoding.
Modify the result of problem P10 in such a way that if an element has no duplicates it is simply copied into the result list. Only elements with duplicates are transferred as (N E) lists."
  (testing "p11"
    (is (= '((4 a) b (2 c) (2 a) d (4 e))
           (list/l99-encode-modified '(a a a a b c c a a d e e e e))))))

(deftest test-l99-repeat
  (testing "l99-repeat"
    (is (= '(x x)
           (list/l99-repeat 2 'x)))
    (is (= '(x x x)
           (list/l99-repeat 3 'x)))))

(deftest test-p12
  "Decode a run-length encoded list.
Given a run-length code list generated as specified in problem P11. Construct its uncompressed version."
  (testing "p12"
    (is (= '(a a a a b c c a a d e e e e)
           (list/l99-decode-run-length '((4 a) b (2 c) (2 a) d (4 e)))))))

(deftest test-p13
  "Run-length encoding of a list (direct solution).
Implement the so-called run-length encoding data compression method directly. I.e. don't explicitly create the sublists containing the duplicates, as in problem P09, but only count them. As in problem P11, simplify the result list by replacing the singleton lists (1 X) by X."
  (testing "p13"
    (is (= '((4 a) b (2 c) (2 a) d (4 e))
           (list/l99-encode-direct '(a a a a b c c a a d e e e e))))))

(deftest test-p14
  "Duplicate the elements of a list."
  (testing "p14"
    (is (= '(a a b b c c c c d d)
           (list/l99-dupli '(a b c c d))))))

(deftest test-p15
  "Replicate the elements of a list a given number of times."
  (testing "p15"
    (is (= '(a a a b b b c c c)
           (list/l99-repli '(a b c) 3)))))

(deftest test-p16
  "Drop every N'th element from a list."
  (testing "p16"
    (is (= '(a b d e g h k) (list/l99-drop '(a b c d e f g h i k) 3)))))

(deftest test-p17
  "Split a list into two parts; the length of the first part is given.
Do not use any predefined predicates."
  (testing "p17"
    (is (= '((a b c) (d e f g h i k))
           (list/l99-split '(a b c d e f g h i k) 3)))))

(deftest test-p18
  "Extract a slice from a list.
Given two indices, I and K, the slice is the list containing the elements between the I'th and K'th element of the original list (both limits included). Start counting the elements with 1."
  (testing "p18"
    (is (= '(c d e f g) (list/l99-slice '(a b c d e f g h i k) 3 7)))))

(deftest test-p19
  "Rotate a list N places to the left."
  (testing "p19"
    (is (= '(d e f g h a b c) (list/l99-rotate '(a b c d e f g h) 3)))
    (is (= '(g h a b c d e f) (list/l99-rotate '(a b c d e f g h) -2)))))

(deftest test-p20
  "Remove the K'th element from a list."
  (testing "p20"
    (is (= '(a c d) (list/l99-remove-at '(a b c d) 2)))))

(deftest test-p21
  "Insert an element at a given position into a list."
  (testing "p21"
    (is (= '(a alfa b c d) (list/l99-insert-at 'alfa '(a b c d) 2)))))

(deftest test-p22
  "Create a list containing all integers within a given range.
If first argument is smaller than second, produce a list in decreasing order."
  (testing "p22"
    (is (= '(4 5 6 7 8 9) (list/l99-range 4 9)))))

(deftest test-p23
  "Extract a given number of randomly selected elements from a list.
The selected items shall be returned in a list."
  (testing "p23"
    (is (= 3 (list/l99-length (list/l99-rnd-select '(a b c d e f g h) 3))))))

(deftest test-p24
  "Lotto: Draw N different random numbers from the set 1..M.
The selected numbers shall be returned in a list."
  (testing "p24"
    (is (= 6 (list/l99-length (list/l99-lotto-select 6 49))))))

(deftest test-p25
  "Generate a random permutation of the elements of a list."
  (testing "p25"
    (is (= 6 (list/l99-length (list/l99-rnd-permu '(a b c d e f)))))))

(deftest test-p26
  "Generate the combinations of K distinct objects chosen from the N elements of a list
In how many ways can a committee of 3 be chosen from a group of 12 people? We all know that there are C(12,3) = 220 possibilities (C(N,K) denotes the well-known binomial coefficients). For pure mathematicians, this result may be great. But we want to really generate all the possibilities in a list.
"
  (testing "p26"
    (is (= '((a b) (a c) (b c)) (list/l99-combination 2 '(a b c))))))

