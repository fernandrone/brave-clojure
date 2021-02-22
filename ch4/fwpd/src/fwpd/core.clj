(ns fwpd.core
  (:require clojure.string))

(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",") (clojure.string/split string #"\n")))

(defn my-parse
  "Convert a CSV into rows of columns"
  [string]
  (as-> string s
    (clojure.string/split s #"\n")
    (map #(clojure.string/split % #",") s)))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row [key value]]
                   (assoc row key (convert key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))
