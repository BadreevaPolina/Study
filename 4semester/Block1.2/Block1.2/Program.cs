using MPI;

namespace Block1
{
    internal class Program
    {
        private static void Main(string[] args)
        {
            using (MPI.Environment env = new MPI.Environment(ref args))
            {
                var comm = Communicator.world;
                var size = comm.Size;
                var rank = comm.Rank;

                if (size >= 4)
                {
                    size = 4;
                }
                else
                {
                    if (rank == 0)
                    {
                        Console.WriteLine("Oops, not enough streams.");
                    }
                    return;
                }

                if (args.Length < 2)
                {
                    if (rank == 0)
                    {
                        Console.WriteLine("Not enough arguments on the command line.");
                    }

                    return;
                }
        
                if (rank == 0)
                {
                    var inputFile = args[0];
                    var outputFile = args[1];
                    int[][] bufferStream = DeclareArrays(inputFile, size); // divide into blocks
                    int support = bufferStream[0][bufferStream[0].Length / 2]; // random elem

                    for (int i = 1; i < size; i++) // send info every stream
                    {
                        comm.Send(support, i, 0);
                        comm.Send<int[]>(bufferStream[i], i, 0);
                    }

                    int[][][] bufferSort = new int[size][][]; //[stream][less and greater than a support][numbers] (comment, test)

                    ReceiveArray(comm, bufferSort, size); // return results                  
                    bufferSort[0] = Sort(bufferStream[0], support);

                    int[] center = new int[] { bufferSort[0][0][bufferSort[0][0].Length / 2],
                        bufferSort[2][1][bufferSort[2][1].Length / 2] }; //random elems
                    
                    // change less and greater for 0-2,1-3
                    comm.Send(center[0], 1, 0);
                    comm.Send<int[]>(bufferSort[1][0].Concat(bufferSort[3][0]).ToArray(), 1, 0);


                    comm.Send(center[1], 2, 0);
                    comm.Send<int[]>(bufferSort[0][1].Concat(bufferSort[2][1]).ToArray(), 2, 0);


                    comm.Send(center[1], 3, 0);
                    comm.Send<int[]>(bufferSort[1][1].Concat(bufferSort[3][1]).ToArray(), 3, 0);

                    bufferSort[0] = Sort(bufferSort[0][0].Concat(bufferSort[2][0]).ToArray(), center[0]);


                    ReceiveArray(comm, bufferSort, size);
                    // change less and greater for 0-1,2-3
                    comm.Send(bufferSort[0][1][bufferSort[0][1].Length / 2], 1, 0);
                    comm.Send<int[]>(bufferSort[0][1].Concat(bufferSort[1][1]).ToArray(), 1, 0);

                    comm.Send(bufferSort[2][0][bufferSort[2][0].Length / 2], 2, 0);
                    comm.Send<int[]>(bufferSort[2][0].Concat(bufferSort[3][0]).ToArray(), 2, 0);

                    comm.Send(bufferSort[2][1][bufferSort[2][1].Length / 2], 3, 0);
                    comm.Send<int[]>(bufferSort[2][1].Concat(bufferSort[3][1]).ToArray(), 3, 0);

                    bufferSort[0] = Sort(bufferSort[0][0].Concat(bufferSort[1][0]).ToArray(),
                        bufferSort[0][0][bufferSort[0][0].Length / 2]);

                    ReceiveArray(comm, bufferSort, size);

                    Console.WriteLine($"{bufferSort[0][0][0]}...{bufferSort[0][1][0]}" +
                        $"...{bufferSort[1][0][0]}...{bufferSort[1][1][0]}...");
                    Console.WriteLine($"{bufferSort[2][0][0]}...{bufferSort[2][1][0]}" +
                        $"...{bufferSort[3][0][0]}...{bufferSort[3][1][0]}...");

                    WriteAnswer(outputFile, bufferSort, size);

                    Console.WriteLine("Ready!");
                }
                else
                {
                    if (rank < size)
                    {
                        int[][] bufferSort = new int[][] { };
                        for (int i = 0; i < size - 1; i++)
                        {
                            int support = comm.Receive<int>(0, 0);
                            int[] bufferStream = comm.Receive<int[]>(0, 0);
                            bufferSort = Sort(bufferStream, support);
                            comm.Send<int[][]>(bufferSort, 0, rank);
                        }
                    }   
                }
            }
        }

      
        private static void WriteAnswer(String outputFile, int[][][] bufferSort, int size)
        {
            var writer = new StreamWriter(outputFile);
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    writer.Write(String.Join(" ", bufferSort[i][j]));
                    if (j != 1 || i != size - 1)
                    {
                        writer.Write(" ");
                    }
                }
            }
            writer.Close();
        }

        private static void ReceiveArray(Intracommunicator comm, int[][][] bufferSort, int size)
        {
            for (int i = 1; i < size; i++)
            {
                bufferSort[i] = comm.Receive<int[][]>(i, i);
            }
        }

        static public int[][] DeclareArrays(String inputFile, int size)
        {
            var reader = new StreamReader(inputFile);
            int[] buffer = reader.ReadToEnd().Split().Select(x => Convert.ToInt32(x)).ToArray();
            reader.Close();

            int range = buffer.Length / size;
            int[][] bufferStream = new int[size][];

            for (int i = 1; i < size; i++)
            {
                bufferStream[i] = new int[range];
            }
            bufferStream[0] = new int[buffer.Length - range * (size - 1)];

            for (int i = 1; i < size * range; i++)
            {
                bufferStream[i % size][i / size] = buffer[i];
            }
            //put the rest of the numbers in A[0]
            for (int i = 0; i < (bufferStream[0].Length - size * range); i++)
            {
                bufferStream[0][range + i] = buffer[size * range + i];
            }
            return bufferStream;
        }

        static public int[][] Compare(int[] array, int support)
        {
            int[] greater = Array.FindAll(array, x =>
                                       x > support);
            int[] less = Array.FindAll(array, x =>
                                       x <= support);
            int[][] mas = new int[][] { less, greater };
            return mas;
        }

        static public int[][] Sort(int[] array, int support)
        {
            int[] greater = Array.FindAll(array, x =>
                                       x > support);
            int[] less = Array.FindAll(array, x =>
                                       x <= support);
            int[][] mas = new int[2][];

            if(less != null)
            {
                mas[0] = SortArray(less, 0, less.Length - 1);
            }
            if (greater != null)
            {
                mas[1] = SortArray(greater, 0, greater.Length - 1);
            }

            return mas;
        }

        static public int[] SortArray(int[] array, int left, int right)
        {
            var l = left;
            var r = right;
            var pivot = array[(left - right) / 2 + right];
            while (l <= r)
            {
                while (array[l] < pivot)
                {
                    l++;
                }
                while (array[r] > pivot)
                {
                    r--;
                }
                if (l <= r)
                {
                    int temp = array[l];
                    array[l] = array[r];
                    array[r] = temp;
                    l++;
                    r--;
                }
            }
            if (left < r)
                SortArray(array, left, r);
            if (l < right)
                SortArray(array, l, right);
            return array;
        }

    }
}
