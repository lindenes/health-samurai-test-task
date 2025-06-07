(ns health-samurai-test-task.my-map)

(map inc [1 2 3 4])

(defn my-map [func coll]
  (loop [cur-coll coll
         new-coll []]
    (if (empty? cur-coll)
      new-coll
      (recur (rest cur-coll) (conj new-coll (func (first cur-coll)))))))

(my-map inc [1 2 3 4])
