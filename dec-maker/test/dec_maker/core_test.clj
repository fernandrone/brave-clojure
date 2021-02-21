(ns dec-maker.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [dec-maker.core :refer [dec2 dec3 dec9]]))

(deftest dec-maker-10
  (testing "Multiple custom decrementors on number 10"
    (is (= 8 (dec2 10)))
    (is (= 7 (dec3 10)))
    (is (= 1 (dec9 10)))))

(deftest dec-maker-5
  (testing "Multiple custom decrementors on number 5"
    (is (= 3 (dec2 5)))
    (is (= 2 (dec3 5)))
    (is (= -4 (dec9 5)))))

(deftest dec-maker-0
  (testing "Multiple custom decrementors on number 0"
    (is (= -2 (dec2 0)))
    (is (= -3 (dec3 0)))
    (is (= -9 (dec9 0)))))
