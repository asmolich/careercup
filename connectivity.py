"""
Given n (of size m) Linked lists

Print all set(head of linked list) of link list that intersect with each other.

e.g.
1-->2-->3-->4-->5
6-->7->88-->4-->5
8->9->10->11->12
13->14->15->12
16->17->18

1 6
8 13
16
"""


def intersected_lists(list_of_lists):
    k = 1000
    uf = [None for i in range(k)]
    result = {}
    for l in list_of_lists:
        head = l[0]
        for elem in l:
            if uf[elem] is None:
                uf[elem] = head
                head_list = set([head])
                result[head] = head_list
            else:
                h = uf[elem]
                if h in result.keys():
                    head_list = result[h]
                    head_list.add(head)

    print uf
    print result
    # for h, v in result:
    #    print v


intersected_lists([
    [1, 2, 3, 4, 5],
    [6, 7, 88, 4, 5],
    [8, 9, 10, 11, 12],
    [13, 14, 15, 12],
    [16, 17, 18]
])
