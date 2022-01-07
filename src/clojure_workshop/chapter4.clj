(ns clojure-workshop.chapter3)

(map (fn [i] (* i 10)) [1 2 3 4 5])

(filter keyword? ["a" :b "c" :d "e" :f "g"])

(filter odd? [1 3 4 5 6])

(remove odd? [1 2 3 4 4 5])

(constantly true)

(filter (constantly true) [1 2 3 4 5])

(filter (constantly false) [1 2 3 4 5])

