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

(defn solution [num lst]
  (println (clojure.string/join "\n"
                                (reduce (fn [new-list x]
                                          (into new-list (repeat num (x)))
                                          '())
                                        lst))))
