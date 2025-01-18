(ns health-samurai-test-task.health-samurai-test-task
  (:gen-class)
  (:require [health-samurai-test-task.reduce-task :as reducer]
            [health-samurai-test-task.flatten-hash-map :as mapper]))

(defn -main
    [& args]
  (println "Hello world"))

(reducer/my-reduce2 + [1 2 3])
;; => 6


(mapper/flatMapper {:aboba 1 :biba 2 :anaconda {:mega 3 :giga 7 :amega {:last 4} } })
;; => {:aboba 1, :biba 2, :anaconda.mega 3, :anaconda.giga 7, :anaconda.amega.last 4}
