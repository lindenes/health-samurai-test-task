(ns health-samurai-test-task.my-group-by)

(defn my-group-by [func listik]
  (loop
   [current-list listik
    result {}]
    (if (empty? current-list)
      result
      (let [first-elem (first current-list)
            tail (rest current-list)]
        (recur
         tail
         (update result (func first-elem) (fn [x] (conj (if x
                                                          x
                                                          []) first-elem))))))))

(my-group-by count ["a" "b" "c" "cc"])
