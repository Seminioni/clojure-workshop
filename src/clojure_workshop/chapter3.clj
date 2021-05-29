(ns clojure-workshop.chapter3
  (:require [java-time :as time]))

(defn print-coords [coords]
  (let [[lat lon] coords]
    (println (str "Latitude: " lat " - " "Longitude: " lon))))

(def airport-data {:lat 48.9615, :lon 2.4372, :code 'LFPB', :name "Paris Le Bourget Airport"})

(defn print-coords2 [airport]
  (let [{lat :lat lon :lon airport-name :name} airport]
    (println (str airport-name " is located at Latitude: " lat " - " "Longitude: " lon))))

(print-coords2 airport-data)

(defn print-coords3 [airport]
  (let [{:keys [lat lon name]} airport]
    (println (str name " is located at Latitude: " lat " - " "Longitude: " lon))))


(def booking [1425, "Bob Smith", "Allergic to unsalted peanuts only", [[48.9615, 2.4372], [37.742, -25.6976]], [[37.742, -25.6976], [48.9615, 2.4372]]])

(let [[id customer-name sensitive-info flight1 flight2 flight3] booking]
  (println id customer-name sensitive-info flight1 flight2 flight3))

(let [big-booking (conj booking [[37.742, -25.6976], [51.1537, 0.1821]] [[51.1537, 0.1821], [48.9615, 2.4372]])
      [_ customer-name _ flight1 flight2 flight3] big-booking]
  (println customer-name flight1 flight2 flight3))


(let [big-booking (conj booking [[37.742, -25.6976], [51.1537, 0.1821]] [[51.1537, 0.1821], [48.9615, 2.4372]])
      [_ customer-name _ & flights] big-booking]
  (println (str customer-name " booked " (count flights) " flights")))

(defn print-flight [flight]
  (let [[departure arrival] flight
        [lat1 lon1] departure
        [lat2 lon2] arrival]
    (println (str "Flying from: Lat " lat1 " Lon " lon1 " Flying to: Lat " lat2 " Lon " lon2))))

(print-flight [[48.9615, 2.4372], [37.742 -25.6976]])

(defn print-booking [booking]
  (let [[_ customer-name _ & flights] booking]
    (println (str customer-name " booked " (count flights) " flights"))
    (let [[flight1 flight2 flight3] flights]
      (when flight1 (print-flight flight1)) 
      (when flight2 (print-flight flight2)) 
      (when flight3 (print-flight flight3)))))

(print-booking booking)

(def mapjet-booking { :id 8773
                     :customer-name "Alice Smith" 
                     :catering-notes "Vegetarian on Sundays" 
                     :flights [{ 
                                :from {
                                       :lat 48.9615 
                                       :lon 2.4372 
                                       :name "Paris Le Bourget Airport"}
                                :to {
                                     :lat 37.742 
                                     :lon -25.6976 
                                     :name "Ponta Delgada Airport"}}
                               {
                                :from {
                                       :lat 37.742 
                                       :lon -25.6976 
                                       :name "Ponta Delgada Airport"}
                                :to {
                                     :lat 48.9615 
                                     :lon 2.4372 
                                     :name "Paris Le Bourget Airport"}}]}) 

(let [{:keys [customer-name flights]} mapjet-booking]
  (println (str customer-name " booked " (count flights) " flights.")))


(defn print-mapjet-flight [flight]
  (let [{{lat1 :lat lon1 :lon} :from
          {lat2 :lat lon2 :lon} :to } flight]
    (println (str "Flying from: Lat " lat1 " Lon " lon1 " Flying to: Lat " lat2 " Lon " lon2))))

(print-mapjet-flight mapjet-booking)

(def marketing-adder (partial + 0.99))

(marketing-adder 10 5)

(def format-price (partial str "$"))

(format-price "15")

(defn sample [coll] 
  (first (shuffle coll)))

(sample [1 2 3 4])

(def sample2 (comp first shuffle))

(sample2 [1 2 3 4])

(def checkout (comp (partial str "Only ") format-price marketing-adder))

(checkout 10 5 6 7 9)

(#(str %1 " " %2 " " %3) "First" "Second" "Third")

(def weapon-fn-map 
  {:fists (fn [health] (if (< health 100) (- health 10) health))
   :staff (partial + 30)
   :sword #(- % 100)
   :cast-iron-saucepan #(- % 100 (rand-int 50))
   :sweet-potato identity})

(defn strike 
  "With one argument, strike a target with a default :fists `weapon`. 
   With two argument, strike a target with `weapon` and return the target entity"
  ([target] (strike target :fists))
  ([target weapon] 
   (let [weapon-fn (weapon weapon-fn-map)]
     (update target :health weapon-fn))))

(def enemy {:name "Arnold", :health 250})

(strike enemy :sword)

(defn mighty-strike [target]
  (let [weapon-fn (apply comp (vals weapon-fn-map))]
    (update target :health weapon-fn)))

(mighty-strike enemy)

(defn invoke
  "Like clojure.core/apply, but doesn't expand/splice the last argument."
  ([f] (f))
  ([f x] (f x))
  ([f x & more] (apply f x more)))

(defmulti strike2 :weapon)

(defmethod strike2 :sword
  [{{:keys [ :health ]} :target}]
  (- health 100))

(strike2 {:weapon :sword :target {:health 140}})

(vec {:a 1})
(vec #{:a :b :c :d})
(vec '(1 2 3))
(vec [1 2 3])

(vector {:a 1})
(vector #{:a :b :c :d})
(vector '(1 2 3))
(vector [1 2 3])

(conj #{:a :b :c} :c :d :a)

(conj #{:a :b :c} #{:a})

(set '(:a :b :c))
(set '(:a :a :b :c :c :c :c :d :d))

(hash-map :a 10, :b 20, :c 30)

(conj {:a 1} {:b 2} [:c 3])

(hash-set 0 2 3)

(last (map (fn [i _] (inc i)) (range) [1 2 3]))

((comp last map) (fn [i _] (inc i)) (range) [1 2 3])



