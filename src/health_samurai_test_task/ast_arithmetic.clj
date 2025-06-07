(ns health-samurai-test-task.ast-arithmetic)

(def ast
  {:operator :/
   :left {:operator :*
          :left {:operator :+
                 :left {:value 3}
                 :right {:value 5}}
          :right {:operator :-
                  :left {:value 2}
                  :right {:value 8}}}
   :right {:operator :+
           :left {:value 4}
           :right {:operator :*
                   :left {:value 6}
                   :right {:value 2}}}})

(defmulti operate (fn [op _ _] op))

(defmethod operate :+ [_ left right]
  (+ left right))

(defmethod operate :- [_ left right]
  (- left right))

(defmethod operate :* [_ left right]
  (* left right))

(defmethod operate :/ [_ left right]
  (/ left right))

(defn evaluate [expr]
  (let [left (if
              (contains? (:left expr) :value)
               (-> expr :left :value)
               (evaluate (:left expr)))
        right (if
               (contains? (:right expr) :value)
                (-> expr :right :value)
                (evaluate (:right expr)))]
    (operate
     (:operator expr)
     left
     right)))

(evaluate ast) ; -3
