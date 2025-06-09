(ns health-samurai-test-task.file-info-map
  (:require [clojure.java.io :as io])
  (:import [java.io File]))

(def file (io/file "/Users/oleg/developing"))

(.getName file)

(.listFiles file)

(.getAbsolutePath file)

(.isDirectory file)

(defn get-file-tree-info [path]
  (let [file (io/file path)
        file-name (.getName path)
        file-type (if (.isFile path)
                    "file"
                    "directory")
        file-children (map #(get-file-tree-info %) (.listFiles file))]
    {:name file-name
     :type file-type
     :children file-children}))

(get-file-tree-info file)
