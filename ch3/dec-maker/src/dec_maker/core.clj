(ns dec-maker.core
  (:gen-class))

(defn dec-maker
  "Create a custom decrementor"
  [dec-by]
  #(- % dec-by))

(defn dec2 [n]
  ((dec-maker 2) n))

(defn dec3 [n]
  ((dec-maker 3) n))

(defn dec9 [n]
  ((dec-maker 9) n))
