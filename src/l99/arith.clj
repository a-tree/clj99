(ns l99.arith
  (:require [l99.list :as list]))

(defn l99-is-prime [n]
  "P31"
  (every?
   #(pos? (rem n %))
   (list/l99-range 2 (Math/sqrt (dec n)))))

(defn l99-gcd [m n]
  "P32"
  (if (> m n)
    (l99-gcd n m)
    (loop [m m
           n n]
      (cond (== m 0) n
            (== m 1) 1
            :else    (recur (rem n m) m)))))

(defn l99-coprime [m n]
  "P33"
  (if (== 1 (l99-gcd m n))
    true
    false))

(defn l99-totient-phi [n]
  "P34"
  (if (== n 1)
    1
    (list/l99-length
     (filter #(true? (l99-coprime n %))
             (list/l99-range 1 (dec n))))))

(defn l99-prime-factors [n]
  "P35"
  (loop [n      n
         primes (filter l99-is-prime (list/l99-range 2 n))
         ret    '()]
    (if (empty? primes)
      (list/l99-reverse ret)
      (let [p (first primes)]
        (if (l99-coprime n p)
          (recur n (rest primes) ret)
          (recur (/ n p) primes (cons p ret)))))))

(defn l99-prime-factors-mult [n]
  "P36"
  (map list/l99-reverse (list/l99-encode (l99-prime-factors n))))

(defn int-pow [n r]
  (reduce * (list/l99-repeat r n)))

(defn l99-improved-totient-phi [n]
  "P37"
  (if (== 1 n)
    1
    (loop [prime-factors-mult (l99-prime-factors-mult n)
           ret                1]
      (if (empty? prime-factors-mult)
        ret
        (let [p (ffirst prime-factors-mult)
              m (first (nfirst prime-factors-mult))]
          (recur (rest prime-factors-mult)
                 (* ret (dec p) (int-pow p (dec m)))))))))

(defn l99-goldbach [n & {:keys [min] :or {min 2}}]
  (if-not (even? n)
    nil
    (let [prime-list (filter l99-is-prime (list/l99-range 2 n))
          gd-list    (for [a     prime-list
                           b     prime-list
                           :when (and (> a min) (> b min))
                           :when (== n (+ a b))] (list a b))]
      gd-list)))

(defn first-goldbach [n & {:keys [min] :or {min 2}}]
  "P40"
  (first (l99-goldbach n :min min)))

(defn l99-goldbach-list [begin end & {:keys [min] :or {min 2}}]
  "P41-1"
  (map #(cons % (first-goldbach % :min min)) (filter even? (list/l99-range begin end))))

;; (defn format-goldbach [l]
;;   (let [ans (first l)
;;         a   (first (rest l))
;;         b   (first (rest (rest l)))]
;;     (str ans " = " a " + " b)))
;; (map format-goldbach (l99-goldbach-list 9 20))
;; ("10 = 3 + 7"
;;  "12 = 5 + 7"
;;  "14 = 3 + 11"
;;  "16 = 3 + 13"
;;  "18 = 5 + 13"
;;  "20 = 3 + 17")

;; 10進数の数値を桁毎のリストにする
;; (defn digits [n & {:keys [base] :or {base 10}}]
;;   (loop [n    n
;;          dlst '()]
;;     (if (< n base)
;;       (cons n dlst)
;;       (recur (quot n base) (cons (rem n base) dlst)))))
