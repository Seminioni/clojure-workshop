(ns clojure-workshop.chapter3
  (:require
    [clojure.string :as str]))

(def game-users
  [{:id 9342
    :username "speedy"
    :current-points 45
    :remaining-lives 2
    :experience-level 5
    :status :active}
   {:id 9854
    :username "stealthy"
    :current-points 1201
    :remaining-lives 1
    :experience-level 8
    :status :speed-boost}
   {:id 3014
    :username "sneaky"
    :current-points 725
    :remaining-lives 7
    :experience-level 3
    :status :active}
   {:id 2051
    :username "forgetful"
    :current-points 89
    :remaining-lives 4
    :experience-level 5
    :status :imprisoned}
   {:id 1032
    :username "wandering"
    :current-points 2043
    :remaining-lives 12
    :experience-level 7
    :status :speed-boost}
   {:id 7213
    :username "slowish"
    :current-points 143
    :remaining-lives 0
    :experience-level 1
    :status :speed-boost}
   {:id 5633
    :username "smarter"
    :current-points 99
    :remaining-lives 4
    :experience-level 4
    :status :terminated}
   {:id 3954
    :username "crafty"
    :current-points 21
    :remaining-lives 2
    :experience-level 8
    :status :active}
   {:id 7213
    :username "smarty"
    :current-points 290
    :remaining-lives 5
    :experience-level 12
    :status :terminated}
   {:id 3002
    :username "clever"
    :current-points 681
    :remaining-lives 1
    :experience-level 8
    :status :active}])

(map (fn [i] (* i 10)) [1 2 3 4 5])

(filter keyword? ["a" :b "c" :d "e" :f "g"])

(filter odd? [1 3 4 5 6])

(remove odd? [1 2 3 4 4 5])

(constantly true)

(filter (constantly true) [1 2 3 4 5])

(filter (constantly false) [1 2 3 4 5])

(take 3 [1 2 3 4 5])

(take 1 '(2 3 4))

(take 0 '(2 3 4))

(take-while (fn [x] (< x 10)) [1 2 3 20 30 40])

(drop-while (fn [x] (< x 10)) [1 2 3 20 30 40])

(def students [{:name "Eliza" :year 1994}
               {:name "Salma" :year 1995}
               {:name "Jodie" :year 1997}
               {:name "Kaitlyn" :year 2000}
               {:name "Alice" :year 2001}
               {:name "Pippa" :year 2002}
               {:name "Fleur" :year 2002}])

(take-while #(< (:year %) 2000) students)

(drop-while #(< (:year %) 2000) students)

(->> [1 2 3 4 5]
  (filter odd?)
  (map #(* 10 %)))

(defn our-range [limit] 
  (take-while #(< % limit) (iterate inc 0)))

(map (fn [i] (print ".") (* i 10)) (our-range 5))

(def our-randoms (repeatedly (partial rand-int 100)))

(take 20 our-randoms)

(defn some-random-integers [size]
  (take size (repeatedly (fn [] (rand-int 100)))))

(some-random-integers 2)

(def animal-names ["turtle" "horse" "cat" "frog" "hawk" "worm"])

(remove #{"horse" "cat"} animal-names)

(def remove-words #{"and" "a" "an" "the" "of" "is"})

(def remove-func (comp remove-words str/lower-case str/trim))

(remove remove-words ["February" " THE " "February" "4th" "and" "a"])

; Exercise 4.07: Using comp and a Set to Filter on a Keyword

(map :status game-users)

(def keep-statuses #{:active :imprisoned :speed-boost})

(def filter-func (comp keep-statuses :status))

(->> game-users
  (filter filter-func)
  (map :current-points))

(def alpha-lc [ "a" "b" "c" "d" "e" "f" "g" "h" "i" "j"])

(mapcat (fn [letter]
          [letter (clojure.string/upper-case letter)]) alpha-lc)

(mapcat #(repeat 2 %) [1 2])

(concat (map #(repeat 2 %) [1 2]) '(1 3))

(defn our-zipmap [xs ys]
  (->> (map (fn [x y] [x y]) xs ys)
       (into {})))

(our-zipmap [:a :b :c] [1 2 3])

(zipmap '(1 2 3) #{:a :b :c})

(into {} [[1 :a] [2 :b]])

; Exercise 4.08: Identifying Weather Trends

(def temperature-by-day
  [18 23 24 23 27 24 22 21 21 20 32 33 30 29 35 28 25 24 28 29 30])

(map (fn [today yesterday]
       (cond 
         (> today yesterday) :warmer
         (= today yesterday) :unchanged
         (< today yesterday) :colder))
  (rest temperature-by-day)
  temperature-by-day)

; Consuming Extracted Data with apply
; + не работает с nil, поэтому нужно отфильтровать коллекцию перед сложением

(let [a 5
            b nil
            c 18]
        (apply + (filter integer? [a b c])))

; Exercise 4.09: Finding the Average Weather Temperature

(/ (apply + temperature-by-day) (count temperature-by-day))

(defn max-value-by-status [field status users]
  (->> users
    (filter (fn [user] (= status (:status user))))
    (map field)
    (apply max 0)))

(defn min-value-by-status [field status users]
  (->> users
    (filter (fn [user] (= status (:status user))))
    (map field)
    (apply min 0)))

(max-value-by-status :experience-level :terminated game-users)
(min-value-by-status :experience-level :imprisoned game-users)


