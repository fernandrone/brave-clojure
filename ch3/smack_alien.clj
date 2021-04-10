(ns smack-alien
  (:gen-class)
  (:require [clojure.string :as string]))

(def asym-alien-body-parts [{:name "head" :size 3}
                            {:name "first-eye" :size 1}
                            {:name "first-ear" :size 1}
                            {:name "mouth" :size 1}
                            {:name "nose" :size 1}
                            {:name "neck" :size 2}
                            {:name "first-shoulder" :size 3}
                            {:name "first-upper-arm" :size 3}
                            {:name "chest" :size 10}
                            {:name "back" :size 10}
                            {:name "first-forearm" :size 3}
                            {:name "abdomen" :size 6}
                            {:name "first-kidney" :size 1}
                            {:name "first-hand" :size 2}
                            {:name "first-knee" :size 2}
                            {:name "first-thigh" :size 4}
                            {:name "first-lower-leg" :size 3}
                            {:name "first-achilles" :size 1}
                            {:name "first-foot" :size 2}])

(defn matching-parts
  [part]
  [{:name (string/replace (:name part) #"^first-" "second-") :size (:size part)}
   {:name (string/replace (:name part) #"^first-" "third-")   :size (:size part)}
   {:name (string/replace (:name part) #"^first-" "fourth-") :size (:size part)}
   {:name (string/replace (:name part) #"^first-" "fifth-")  :size (:size part)}])

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set (concat [part] (matching-parts part)))))
          []
          asym-body-parts))

(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

(defn -main
  []
  (better-symmetrize-body-parts asym-alien-body-parts))

(defn solution [num lst]
  (println (clojure.string/join "\n"
                                (reduce (fn [new-list x]
                                          (into new-list (repeat num (x)))
                                          '())
                                        lst))))
