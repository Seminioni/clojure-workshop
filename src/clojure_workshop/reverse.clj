(defn my-reverse [coll]
  (letfn [(iter [coll result]
                (if (empty? coll) result (iter (drop-last coll) (conj result (last coll)))))]
         (iter coll [])))

(my-reverse [1 2 3])

(my-reverse (sorted-set 5 7 2 7))

(my-reverse [[1 2][3 4][5 6]])
