(ns unchuncked-vampmatic (:gen-class))

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
  (->> social-security-numbers
       (unchunk)
       (map vampire-related-details)
       (filter vampire?)
       (first)))

  ;; (first (filter vampire?
  ;;                (map vampire-related-details (unchunk social-security-numbers)))))

; (time (identify-vampire (range 0 1000000)))
; => "Elapsed time: 3000.488288 msecs"
; =>  {:makes-blood-puns? true, :has-pulse? false, :name "Damon Salvatore"}

;; Here we turned the collection 'social-security-numbers' into something not chunkable and got *some* performance for it. It takes 3 seconds because that is the time to move accross all the first 3 itens until Damon Salvatore is found!
