(ns clojure-workshop.calculator)

(def operations-map {
                     "+" +
                     "-" -
                     "*" *
                     })

(defn operation? [symbol]
  (contains? operations-map symbol))

(defn infix->polish
  [symbols]
  (if (empty? symbols)
    0
   (flatten (reduce (fn [acc symbol]
              (let [output-vec (first acc)
                    stack (second acc)
                    is-number (number? symbol)
                    is-operation (operation? symbol)]
                    (cond
                        is-number [(conj output-vec symbol) stack]
                        is-operation (let [stack-empty? (empty? stack)
                                           last-value (first stack)]
                                            (if (not stack-empty?)
                                              [(conj output-vec last-value) (conj (rest stack) symbol)]
                                              [output-vec (conj (rest stack) symbol)]))))) [[] []] symbols))))


(infix->polish [1 "+" 2 "+" 3 "-" 1 "+" 1 "-" 9])
