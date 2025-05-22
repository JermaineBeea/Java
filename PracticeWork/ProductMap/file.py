from itertools import product

set1 = 'a', 'b', 'c', 'd', 'e', 'f', 'g'
set2 = 1, 2, 3, 4, 5
set3 = 'A', 'B', 'C'

iterations = 17

    

def sumFunction(qunatity:int):
    result = 0
    for n in range(1, qunatity):
        result += (function(n) - 1) // (3) * 3
    return result

def funtion(index:int):
    if index == 1: return iterations
    return iterations - sumFunction(index)