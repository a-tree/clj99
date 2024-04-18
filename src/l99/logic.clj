(ns l99.logic
  (:require [l99.list :as list]))


(defn l99-logic-table [A B f]
  "P46"
  (map
   (fn [l] (vector l (f l)))
   (distinct
    (list/l99-combination 2 (list/l99-flatten-list (cons A B))))))
