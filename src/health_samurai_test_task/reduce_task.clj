(ns health-samurai-test-task.reduce-task)

(defn my-reduce
  [fn list acc]
   (if (empty? list)
    acc
    (recur fn (rest list) (fn (first list) acc)))
  )

(defn my-reduce2
  [fn list]
  (loop [remaining (rest list)
         result (first list)]
    (if (empty? remaining)
      result
      (recur (rest remaining) (fn result (first remaining))))))



;; (defn my-reduce
;;   ([fn list acc]
;;   (if (empty? list)
;;     acc
;;     (recur fn (rest list) (fn (first list) acc))))
;;    ([fn list] (my-reduce fn (rest list) (first list)))
;;   )
