(defn first-n-fib [num]
  (letfn [(fib [i]
            (cond
              (= i 0) 0
              (= i 1) 1
              (> i 1) (+ (fib (- i 2)) (fib (- i 1)))))
          (gen-fib-seq [n coll]
            (if (= n 0) coll (conj coll (gen-fib-seq (dec n) [(fib n)]))))]
    (->> (gen-fib-seq num [])
         flatten
         reverse)))

(first-n-fib 6)
