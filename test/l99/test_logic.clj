(ns l99.test-logic
  (:require [clojure.test :refer :all]
            [l99.list :as list]
            [l99.logic :as logic]))

(deftest test-p46-1
  "Truth tables for logical expressions.
    Define functions and, or, nand, nor, xor, impl and equ (for logical equivalence) which return the result of the respective operation on boolean values.

    A logical expression in two variables can then be written in prefix notation, as in the following example: (and (or A B) (nand A B)).

    Write a function table which prints the truth table of a given logical expression in two variables.

    Example:
    * (table 'A 'B '(and A (or A B))).
    true true true
    true nil true
    nil true nil
    nil nil nil 
"
  (testing "p46 only true"
    (is (= (list true true true)
           (flatten
            (logic/l99-logic-table
             '(true)
             '(true)
             #(and (first %) (second %))))))))


