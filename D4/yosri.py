import random
from typing import List

NUM_OF_FRAMES = random.randint(1, 7)


def fifo(reference_strings: List[int]) -> int:

    res = []
    ram = set()
    page_fault = 0

    for i in reference_strings:
        if len(ram) < NUM_OF_FRAMES:
            if i not in ram:
                page_fault += 1
                ram.add(i)
                res.append(i)

        elif len(ram) == NUM_OF_FRAMES:
            if i not in ram:
                answer = res.pop(0)
                ram.remove(answer)
                ram.add(i)
                res.append(i)
                page_fault += 1

        print(ram)
            
    return page_fault

def lru(reference_strings: List[int]) -> int:
    page_faults = 0
    stack = []
    ram = []
    print(reference_strings)

    for num in reference_strings:
        # ram not full
        if len(ram) < NUM_OF_FRAMES:
            if num not in ram:
                ram.append(num)
                stack.append(num)
                page_faults += 1

        # ram is full
        else:
            if num not in ram:
                answer = stack[0]
                idx = ram.index(answer)
                ram.insert(idx, num)
                ram.remove(answer)
                stack.remove(answer)
                stack.append(num)
                page_faults += 1
            else:
                stack.remove(num)
                stack.append(num)
        print(ram)
        
    return page_faults

def main():
    reference_string = [random.randint(0, 9) for _ in range(12)]

    print(f'Performing FIFO on {reference_string}, page frames: {NUM_OF_FRAMES}')
    print(f'page fault from FIFO: {fifo(reference_string)}')
    print()
    print(f'Performing LRU on {reference_string}, page frames: {NUM_OF_FRAMES}')
    print(f'page fault from LRU: {lru(reference_string)}')

if __name__ == '__main__':
    main()