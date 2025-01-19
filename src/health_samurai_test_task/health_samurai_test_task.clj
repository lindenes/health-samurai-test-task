(ns health-samurai-test-task.health-samurai-test-task
  (:gen-class)
  (:require [health-samurai-test-task.reduce-task :as reducer]
            [health-samurai-test-task.flatten-hash-map :as mapper]
            [health-samurai-test-task.my-query-parser :as parser]))

(defn -main
    [& args]
  (println "Test my reducer: test data [1 2 3 4]")
  (println "Result: " (reducer/my-reduce + [1 2 3 4] 0))
  (println "Test hash-map flat-mapper: test data {:aboba 1 :biba 2 :anaconda {:mega 3 :giga 7 :amega {:last 4} } } ")
  (println "Result: " (mapper/flat-mapper {:aboba 1 :biba 2 :anaconda {:mega 3 :giga 7 :amega {:last 4} } }))
  (println "Test my query parser:test data  https//some-site.com?a=1&a=2&b=3")
  (println "Result: " (parser/my-get-query-params "https//some-site.com?a=1&a=2&a=4&b=3")))

(reducer/my-reduce2 + [1 2 3])
;; => 6


(mapper/flat-mapper {:aboba 1 :biba 2 :anaconda {:mega 3 :giga 7 :amega {:last 4} } })
;; => {:aboba 1, :biba 2, :anaconda.mega 3, :anaconda.giga 7, :anaconda.amega.last 4}

(parser/my-get-query-params  "https//some-site.com?a=1&a=2&a=4&b=3")
;; => {:a ["1" "2" "4"], :b "3"}
