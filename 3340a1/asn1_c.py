#nicholas gardi - 250868721
import sys

size = int(sys.argv[1])
k = int(sys.argv[2])

array = []
for i in range(size, 0, -1):
    array.append(i)


def insertionSort(array):
    for i in range(1, len(array)):
        key = array[i]
        j = i-1
        while j >= 0 and key < array[j]:
                array[j+1] = array[j]
                j -= 1
        array[j+1] = key

def mergeSort(array, k):
    if len(array)>1:
        if len(array) <= k:
            insertionSort(array)
        else:
            mid = len(array)//2
            lefthalf = array[:mid]
            righthalf = array[mid:]

            mergeSort(lefthalf, k)
            mergeSort(righthalf, k)

            i=0
            j=0
            k=0
            while i < len(lefthalf) and j < len(righthalf):
                if lefthalf[i] < righthalf[j]:
                    array[k]=lefthalf[i]
                    i=i+1
                else:
                    array[k]=righthalf[j]
                    j=j+1
                k=k+1

            while i < len(lefthalf):
                array[k]=lefthalf[i]
                i=i+1
                k=k+1

            while j < len(righthalf):
                array[k]=righthalf[j]
                j=j+1
                k=k+1



#OUTPUT
[print("array before sort: ")]
for i in range(0, 20):
    if i == 19:
         print(array[i])
    else:
        print(array[i], end=', ')
mergeSort(array, k)
print()
[print ("array after sort: ")]
for i in range(0, 20):
    if i == 19:
         print(array[i])
    else:
        print(array[i], end=', ')
