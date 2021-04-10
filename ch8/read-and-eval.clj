(ns read-and-eval (:gen-class)
    (:require [clojure.test :refer [deftest is testing run-tests]]))

;; (defn infix
;;   [string]
;;   (let [lst (read-string string)]
;;     (loop [acc (list (second lst) (first lst))
;;            lst (nthrest lst 2)]
;;       (if (empty? lst)
;;         acc
;;         (recur (conj acc (list (second lst) (first lst))) (nthrest lst 2))))))

;; (deftest test-infix
;;   (testing "Infix"
;;     (is (= '(- 5 (+ 1 (* 3 4))) (infix "(1 + 3 * 4 - 5)")))))

;; (run-tests)
