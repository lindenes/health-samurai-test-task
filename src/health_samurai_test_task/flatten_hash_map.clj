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


(defn flatMapper
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
