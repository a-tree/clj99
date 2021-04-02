(ns l99.core
  (:require [clojure.tools.cli :as cli])
  (:gen-class))

(def l99-options
  [["-h" "--help" "Show Help" ]
   ;; additional options
   ])

(def usage
  (str
   "Usage: "
   "l99 [option] ...\n"
   "\n"
   "-h, --help      display this help and exit.\n"))

(defn -main
  [& args]
  "l99 main"
  (let [{:keys [options      ;; The options map, keyed by :id, mapped to the parsed value
                arguments    ;; A vector of unprocessed arguments
                summary      ;; A string containing a minimal options summary
                errors]}     ;; A possible vector of error message strings generated during parsing; nil when no errors exist
        (cli/parse-opts args l99-options)]
    (when (:help options)
      (println usage)
      (System/exit 0))
    (println "Hello l99.")))
