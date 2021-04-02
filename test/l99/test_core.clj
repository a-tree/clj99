(ns l99.test-core
  (:require [l99.core :as core]
            [clojure.tools.cli :as cli]
            [clojure.test :refer :all]))

(deftest test-l99-core
  (testing "test the l99"
    (is (= 42 42))))
