(ns mapset.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [mapset.core :refer [mapset]]))

(deftest test-inc
  (testing "mapset with inc"
    (is (= (mapset inc [1 1 2 2]) #{2 3}))))

(deftest test-plus
  (testing "mapset with +"
    (is (= (mapset + [1 2 3] [4 5 6]) #{5 7 9}))))
