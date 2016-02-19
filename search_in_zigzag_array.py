"""
An array consist of elements whose difference is positive or negative 1. Find given element.

Say
int arr[]={1,2,3,4,3,4,5,6,7}
To find : 6

http://www.careercup.com/question?id=6315677910564864
Goal is sublinear algo.
"""
def search(arr, n):
    if arr is None or n is None or len(arr) == 0:
        raise Exception("Invalid Argument")
    return search0(arr, 0, len(arr) - 1, n)

def search0(arr, start, end, n):
    #print "[%s, %s]" % (start, end)
    diff = abs(arr[start] - arr[end])
    index_diff = end - start
    if diff == index_diff:
        res = start + abs(arr[start] - n)
        if res > end or arr[res] != n:
            result = -1
        else:
            result = res
    else:
        left = -1
        right = -1
        if end / 2 - 1 > start:
            left = search0(arr, start, end / 2 - 1, n)
        if start + index_diff / 2 < end:
            right = search0(arr, start + index_diff / 2, end, n)
        if left != -1:
            result = left
        elif right != -1:
            result = right
        else:
            result = -1
    #print "[%s, %s], arr[%s, %s] => %s" % (start, end, arr[start], arr[end], result)
    return result

print search([1,2,3,4,3,4,5,6,7], 6)
print search([1,2,3,4,5,6,5,4,3,2,1,2,3,4,5,6,7,8], 8)
print search([1,2,3,4,5,4,3,2,1,0,-1], 6)

