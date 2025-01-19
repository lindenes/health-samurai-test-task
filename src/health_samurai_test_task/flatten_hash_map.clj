(ns health-samurai-test-task.flatten-hash-map)


(defn my-merge
  [key-name mapa]
   (loop [current-map (seq mapa)
          accum {}]
     (if (empty? current-map)
       accum
      (let [[key value] (first current-map)]
        (recur (rest current-map) (assoc accum (symbol (str key-name "." (name key))) value))
        )
       )
     )

  )


(defn flat-mapper
  [mapa]
  (loop [current-map (seq mapa)
         accum {}]
    (if (empty? current-map)
      accum
       (let [[key value] (first current-map ) ]
         (if (map? value)
           (recur (merge accum (my-merge key value)) accum )
           (recur (rest current-map) (assoc accum key value)))
         )
      )
    )
  )


; senior solution
(def test-data {:a {:b 1
                    :c "xxx"}
                :c 2})

(defn make-key [path]
  (keyword (clojure.string/join "." (map name path))))

(defn f [data path]
  (let [all-keys (keys data)]
    (into {} (map (fn [k]
                    (if (map? (k data))
                      (f (k data) (conj path k))
                      [(make-key (conj path k)) (k data)])) all-keys))))

(f test-data []) {:a.b 1, :a.c "xxx", :c 2}
