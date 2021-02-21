(ns mapset.core
  (:gen-class))

(defn mapset
  ([f]
   (set (map f)))
  ([f coll]
   (set (map f coll)))
  ([f c1 c2]
   (set (map f c1 c2)))
  ([f c1 c2 c3]
   (set (map f c1 c2 c3))))
