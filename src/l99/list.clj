(ns l99.list)

(defn l99-last [lst]
  "P01"
  ;; (-> lst last list))
  (loop [car (first lst)
         cdr (rest lst)]
    (if (empty? cdr)
      (list car)
      (recur (first cdr) (rest cdr)))))

(defn l99-but-last [lst]
  "P02"
  (if (== 2 (count lst))
    lst
    (l99-but-last (rest lst))))

(defn l99-element-at [lst at]
  "P03"
  (if (pos? at)
    ;; (nth lst (dec at))))
    (loop [lst lst
           n   1]
      (if (== n at)
        (first lst)
        (recur (rest lst) (inc n))))))

(defn l99-length [lst]
  "P04"
  ;; (count lst))
  (loop [lst lst
         n   0]
    (if (empty? lst)
      n
      (recur (rest lst) (inc n)))))

(defn l99-reverse [lst]
  "P05"
  ;; (reverse lst))
  (loop [lst lst
         ret '()]
    (if (empty? lst)
      ret
      (recur (rest lst)
             ;; (conj ret (first lst))))))
             (concat [(first lst)] ret)))))

(defn l99-palindrome? [lst]
  "P06"
  (= lst (l99-reverse lst)))

(defn l99-flatten-list [lst]
  "P07"
  ;; (flatten lst))
  (if (seq? lst)
    (if-not (empty? lst)
      (concat (l99-flatten-list (first lst))
              (l99-flatten-list (rest  lst))))
    (list lst)))

(defn l99-compress [lst]
  "P08"
  ;; (dedupe lst))
  (loop [lst lst
         ret '()]
    (cond (empty? lst) (l99-reverse ret)
          (= (first ret) (first lst)) (recur (rest lst) ret)
          :else (recur (rest lst) (cons (first lst) ret)))))

(defn l99-pack [lst]
  "P09"
  (loop [x   (first lst)
         xs  (rest lst)
         sub (list x)
         ret '()]
    (if (empty? xs)
      (l99-reverse (cons sub ret))
      (if (= x (first xs))
        (recur x (rest xs) (cons x sub) ret)
        (recur (first xs) (rest xs) (list (first xs)) (cons sub ret))))))

(defn l99-encode [lst]
  "P10"
  (map (fn [lst] (list (l99-length lst) (first lst))) (l99-pack lst)))


(defn l99-encode-modified [lst]
  "P11"
  (map (fn [x] (if (< (l99-length x) 2)
                 (first x)
                 (list (l99-length x) (first x))))
       (l99-pack lst)))

(defn l99-repeat [n x]
  (loop [n   n
         ret '()]
    (if (< n 1)
      ret
      (recur (dec n) (cons x ret)))))

(defn l99-decode-run-length [lst]
  "P12"
  (l99-flatten-list
   (map (fn [x] (if (coll? x)
                  (l99-repeat (first x) (first (rest x)))
                  x)) lst)))

(defn l99-encode-direct [lst]
  "P13"
  (loop [x   (first lst)
         xs  (rest lst)
         n   1
         ret '()]
    (if (empty? xs)
      (l99-reverse
       (map (fn [lst]
              (if (< (first lst) 2)
                (first (rest lst))
                lst))
            (cons (list n x) ret)))
      (if (= x (first xs))
        (recur x (rest xs) (inc n) ret)
        (recur (first xs) (rest xs) 1 (cons (list n x) ret))))))

(defn l99-dupli [lst]
  "P14"
  (l99-flatten-list
   (map (fn [x] (l99-repeat 2 x)) lst)))

(defn l99-repli [lst n]
  "P15"
  (l99-flatten-list
   (map (fn [x] (l99-repeat n x)) lst)))

(defn l99-drop [lst n]
  "P16"
  (loop [lst lst
         c   1
         ret '()]
    (if (empty? lst)
      (l99-reverse ret)
      (if (= c n)
        (recur (rest lst) 1 ret)
        (recur (rest lst) (inc c) (cons (first lst) ret))))))

(defn l99-split [lst n]
  "P17"
  (loop [lst lst
         c   0
         sub '()]
    (if (= c n)
      (list (l99-reverse sub) lst)
      (recur (rest lst)
             (inc c)
             (cons (first lst) sub)))))

(defn l99-slice [lst begin end]
  "P18"
  (l99-reverse
   (second (l99-split (l99-reverse (second (l99-split lst (dec begin))))
                      (- end begin 1)))))

(defn l99-rotate [lst n]
  "P19"
  (if-not (neg? n)
    (loop [lst lst
           c   (dec n)]
      (if (neg? c)
        (apply list lst)
        (recur (concat (rest lst) (list (first lst)))
               (dec c))))
    (l99-reverse (l99-rotate (l99-reverse lst) (- n)))))

(defn l99-remove-at [lst n]
  "P20"
  (loop [lst lst
         c   1
         ret '()]
    (if (empty? lst)
      (l99-reverse ret)
      (if (= c n)
        (recur (rest lst) (inc c) ret)
        (recur (rest lst) (inc c) (cons (first lst) ret))))))

(defn l99-insert-at [s lst n]
  "P21"
  (loop [lst lst
         c 1
         ret '()]
    (if (empty? lst)
      (l99-reverse ret)
      (if (= c n)
        (recur lst (inc c) (cons s ret))
        (recur (rest lst) (inc c) (cons (first lst) ret))))))

(defn l99-range [begin end]
  "P22"
  ;; (range begin (inc end)))
  (filter #(>= % begin)
          (take (inc end)
                (lazy-cat [0] (iterate inc 1)))))
;; (loop [c begin
;;        ret '()]
;;   (if (> c end)
;;     (l99-reverse ret)
;;     (recur (inc c)
;;            (cons c ret)))))

(defn l99-rnd-select [lst n]
  "P23"
  (let [rand_max (l99-length lst)]
    (loop [lst (l99-rotate lst (inc (rand-int rand_max)))
           c (dec n)
           ret '()]
      (if (neg? c)
        (apply list ret)
        (recur (l99-rotate (rest lst) (rand-int rand_max))
               (dec c)
               (cons (first lst) ret))))))

(defn l99-lotto-select [n max]
  "P24"
  (l99-rnd-select (l99-range 1 max) n))

(defn l99-rnd-permu [lst]
  "P25"
  (l99-rnd-select lst (l99-length lst)))

(defn l99-combination [n lst]
  "P26"
  (cond (zero? n) '(())
        (empty? lst) '()
        :else (concat (map (fn [y] (cons (first lst) y))
                           (l99-combination (dec n) (rest lst)))
                      (l99-combination n (rest lst)))))
