(ns health-samurai-test-task.my-query-parser
  (:require [clojure.string :as str]))


(defn vektorization [old-data add-data]
    (if (vector? old-data)
      (conj old-data add-data)
      [old-data add-data])
  )

(defn my-get-query-params [query]
    (-> (str/split query #"\?")
        second
        (str/split #"&")
        (->> (map (fn [x] (str/split x #"=") )))
        (->> (reduce (fn [acc [key value]]
                       (let [open-key (symbol (str ":" key))
                             old-value (get acc open-key)]
                         (if (get acc open-key)
                            (assoc acc open-key (vektorization old-value value))
                            (assoc acc open-key value)
                           )
                         ))
                       {}))
        )
  )
