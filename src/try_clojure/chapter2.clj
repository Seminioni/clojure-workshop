(ns clojure-workshop.chapter2)

(clojure.string/replace "Hello World" #"\w" "!")

(defn encode-letter
  [s x]
  (let [code (Math/pow (+ x (int (first (char-array s)))) 2)]
    (str "#" (int code))))

(defn encode
  [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\w" (fn [s]
                                      (encode-letter s number-of-words)))))

;; (encode "Super secret message")

(defn decode-letter
  [x y]
  (let [number (Integer/parseInt (subs x 1))
        letter (char (- (Math/sqrt number) y))]
    (str letter)))
  
(defn decode
  [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\#\d+" (fn [s]
                                         (decode-letter s number-of-words)))))

(decode (encode "Super secret message"))

(def favorite-fruit { :name "apple" :color "yellow" :kcal_per_100g 100})

(get favorite-fruit :name)

(get favorite-fruit :color)

(get favorite-fruit :unknown) ; nil

(get favorite-fruit :unknown "Hey")

(favorite-fruit :name)

(:name favorite-fruit)

(:unknow favorite-fruit "default")

(assoc favorite-fruit :form "round")

(assoc favorite-fruit :name "cucumber")


(assoc favorite-fruit :yearly_production_in_tonnes {
                                                    :china 2025000
                                                    :italy 541000
                                                    :new_zealand 412000
                                                    :iran 311000
                                                    :chile 225000})

(update favorite-fruit :kcal_per_100g dec)

(dissoc favorite-fruit :name)

#{1 2 3 4 5} ; #{1 4 3 2 5}

#{:a :b :c}

(hash-set :a :b :d :c)

(set [:a :b :c])

(set '(1 2 3))

(set #{1 2 3})

(set {:a 2 :b 4})

(set [:a :a :b])

(def supported-currencies 
  #{"Dollar" 
    "Japanese yen"
    "Euro" 
    "Indian rupee" 
    "British pound"})

(get supported-currencies "Dollar")

(conj supported-currencies "Russian ruble")

(vector 1 2 3 4)

[9292929]

(def fibonnacci [0 1 1 2 3 5 8 13 21])

(def my-todo (list "Go to gym" "Feed the dog" "Go for a wolk" "Code"))

(cons "Sleep" my-todo)

(conj my-todo "Kek")

(conj my-todo "Kek" "Pek" "FP")

(def languages {:name "Clojure" :creator "Rich Hickey" :platforms ["Java" "JS" ".Net"]})

(count languages)

(empty? languages)

(seq languages)

(into [1 2 3 4] #{5 6 7 8})

(into #{5 6 7 8} [1 2 3 4])

(into #{5 6 7 8} '(1 2 3 4))

(into #{5 6 7 8} {:a 1})

(def gemstone-db {:ruby {:name "Ruby"
                         :stock 120
                         :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712]
                         :properties {:dispersion 0.018
                                      :hardness 9.0
                                      :refractive-index [1.77 1.78]
                                      :color "Red"}}
                  :diamond {:name "Diamond"
                            :stock 10
                            :sales [8295 329 5960 6118 4189 3436 9833 8870 9700 7182 7061 1579]
                            :properties {:dispersion 0.044
                                         :hardness 10
                                         :refractive-index [2.417 2.419]
                                         :color "Typically yellow, brown or gray to colorless"}}
                  :moissanite {:name "Moissanite"
                               :stock 45
                               :sales [7761 3220]
                               :properties {:dispersion 0.104
                                            :hardness 9.5
                                            :refractive-index [2.65 2.69]
                                            :color "Colorless, green, yellow"}}})

(get (get (get gemstone-db :ruby) :properties) :hardness)

(:hardness (:properties (:ruby gemstone-db)))

;; (doc get-in)

(get-in gemstone-db [:ruby :properties :hardness])

(defn durability 
  [db gemstone]
  (get-in db [gemstone :properties :hardness]))

(durability gemstone-db :ruby)

;; (pprint 
;;  (assoc-in gemstone-db [:ruby :properties :color] "Near colorless through pink through all shades of red to a deep crimson"))

(defn change-color
  "Change color of gemstone"
  [db gemstone color]
  (assoc-in db [gemstone :properties :color] color))

(change-color gemstone-db :moissanite "Red")

;; (pprint (update-in gemstone-db [:moissanite :stock] dec))

(update-in gemstone-db [:diamond :sales] conj 999)

(defn sell
  [db gemstone client-id]
  (update-in db [gemstone :sales] conj client-id))

(sell gemstone-db :diamond 111)

(def memory-db (atom {}))
(defn read-db [] @memory-db)
(defn write-db [new-db] (reset! memory-db new-db))

(defn create-table
  "Create table in in-memory db"
  [table-name]
  (let [db (conj (read-db) { table-name { :data [] :indexes {}}})]
    (write-db db)))

(create-table :test)
(create-table :test2)

(defn drop-table
  "Drop table by table-name"
  [table-name]
  (let [db (read-db)]
    (write-db (dissoc db table-name))))

;; (drop-table :test2)

(defn insert 
  "Insert key in table at db"
  [table-name record id-key]
  (let [db (read-db)
        new-db (update-in db [table-name :data] conj record)
        index (- (count (get-in new-db [table-name :data])) 1)]
    (if (contains? (get-in new-db [table-name :indexes]) id-key)
      (println "Record with" id-key (id-key record) "already exists. Aborting")
      (write-db (update-in new-db [table-name :indexes id-key] assoc (id-key record) index)))))


(insert :test {:name "Pear" :stock 3} :name)
(insert :test {:name "Banan" :stock 2} :name)
(insert :test {:man "Apple" :stock 2} :man)

(pprint (insert :test2 {:name "Banan" :stock 2} :name))

(defn select-*
  "Function will return all the records of a table passed as a parameter"
  [table-name]
  (let [db (read-db)
        records (get-in db [table-name :data])] records))
  
(select-* :test)

(defn select-*-where
  "Function should use the index map to retrieve the index of the record
   in the data vector and return the element"
  [table-name field field-value]
  (let [db (read-db)
        table-indexes (get-in db [table-name :indexes])
        entity (table-indexes field)
        index (entity field-value)
        result (get (select-* table-name) index)] result))

(select-*-where :test :name "Pear")
