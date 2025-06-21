(ns health-samurai-test-task.map-schema-validator)

(defn check-type [value]
  (cond
    (map? value)
    "map"
    (string? value)
    "string"))

(defn valid? [schema value]
  (let [value-type (check-type value)
        schema-type (:type schema)
        elements (:elements schema)
        keys-list (keys elements)
        valid-keys (mapv
                    (fn [k] (valid? (-> schema :elements k) (get value k)))
                    (if (map? value)
                      (keys value)
                      []))]
    (println valid-keys keys-list elements)
    (and (= value-type schema-type) (every? true? valid-keys))))

(def schema
  {:type "map",
   :elements {:a {:type "string"}
              :b {:type "map"
                  :elements {:c {:type "string"}}}}})

(def schema2
  {:type "string"})

(valid? schema2 "")

(valid? schema {}) ;; true

(valid? schema {:a "foobar"})

(valid? schema {:a "hello"
                :b {:c "hi"}})

(valid? schema {:a [1 2 3]})

(valid? schema {:a "hello"
                :b "str"})


