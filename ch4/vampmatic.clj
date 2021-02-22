(ns vampmatic (:gen-class))

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn unchunk [s]
  (when (seq s)
    (lazy-seq
     (cons (first s)
           (unchunk (next s))))))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

; (time (identify-vampire (range 0 1000000)))
; => "Elapsed time: 32004.482398 msecs"
; => {:makes-blood-puns? true, :has-pulse? false, :name "Damon Salvatore"}

;; A nonlazy implementation of map would first have to apply vampire-related-details to every member of social-security-numbers before passing the result to filter. Because you have one million suspects, this would take one million seconds, or 12 days, and half your city would be dead by then! Of course, if it turns out that the only vampire is the last suspect in the record, it will still take that much time with the lazy version, but at least there’s a good chance that it won’t.

;; However, although it didn't take one million seconds, it didn't take one second either! The reason it took 32 seconds is that Clojure chunks its lazy sequences, which just means that whenever Clojure has to realize an element, it preemptively realizes some of the next elements as well.
