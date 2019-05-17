#nicholas gardi - 250868721
import sys

size = int(sys.argv[1])

array = []
for i in range(size, 0, -1):
    array.append(i)

def mergeSort(array):
    if len(array)>1:
        mid = len(array)//2
        lefthalf = array[:mid]
        righthalf = array[mid:]

        mergeSort(lefthalf)
        mergeSort(righthalf)

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
mergeSort(array)
print()
[print ("array after sort: ")]
for i in range(0, 20):
    if i == 19:
         print(array[i])
    else:
        print(array[i], end=', ')
