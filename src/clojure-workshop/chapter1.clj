(ns clojure-workshop.chapter1)

(if true "Yes" "No")

(if false (+ 3 4) (rand))

(do (* 3 4) (/ 8 4) (+ 1 1))

(do
  (println "A proof that this is executed")
  (println "And this too"))
  
(if true 
  (do 
    (println "Calculating a random number...")
    (rand))
  (+ 1 2))

(if true
  (do
    (println "Calculating a random number...")
    (rand)))

(when true
  (do
    (println "Calculating a random number...")
    (rand)))

(def x 10) 

(println x)

(inc x)

(let [y 3] 
  (println y)
  (* 10 y))

(let [x 3] (println x))

(let [x (* 10 3)
      y 2
      z 10
      message "Kokoko"]
  (println message)
  (+ x y z))

(fn [])

((fn [x] (* x x)) 2)

(def square (fn [x] (* x x)))

(square 2)

(defn meditate [s calm]
  (println "Clojure Meditate v1.0")
  (if calm
    (clojure.string/capitalize s)
    (str (clojure.string/upper-case s)
         "!")))

(meditate "fuck" true)

(defn square
  "Return the product of the number 'x' with itself"
  [x]
  (* x x))

(doc square)

(def base-co2 382)
(def base-year 2006)

(defn co2-estimate
  "Returns a (conservative) year's estimate of carbon dioxide parts per million in
    the atmosphere"
  [year]
  (let [year-diff (- year base-year)]
    (+ base-co2 (* 2 year-diff))))
    
(doc co2-estimate)

(co2-estimate 2050)

(true? true)

(defn meditate3
  "I know how to meditate"
  [calmness-level s]
  (println "Clojure Meditate v2.0"
    (cond 
      (< calmness-level 5) (str (clojure.string/upper-case s) " YA TELL YOU")
      (<= 5 calmness-level 9) (str (clojure.string/capitalize s))
      (= calmness-level 10) (str (clojure.string/reverse s)))))
    
     
(meditate3 2 "Mijgan")

