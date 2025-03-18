import java.io.*;
import java.util.*;


public class BOJ20006 {

    private static class Player implements Comparable<Player> {
        private int level;
        private String nickname;

        public Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }

        @Override
        public int compareTo(Player other) {
            return this.nickname.compareTo(other.nickname);
        }
    }

    private static class Room {
        private int firstPlayerLevel;
        private List<Player> players = new ArrayList<>();

        public Room(Player firstPlayer) {
            this.firstPlayerLevel = firstPlayer.level;
            this.players.add(firstPlayer);
        } 
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());  // 플레이어 수
        int m = Integer.parseInt(st.nextToken());  // 방의 정원
        
        List<Room> rooms = new LinkedList<>();
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String nickname = st.nextToken();

            Player curPlayer = new Player(l, nickname);
            boolean flag = false;
            for (int j = 0; j < rooms.size(); j++) {
                Room curRoom = rooms.get(j);
                int roomLevel = curRoom.firstPlayerLevel;

                if (curRoom.players.size() == m) {
                    continue;
                }
                if (roomLevel - 10 <= l && roomLevel + 10 >= l) {
                    curRoom.players.add(curPlayer);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                rooms.add(new Room(curPlayer));
            }
        }

        for (int i = 0; i < rooms.size(); i++) {
            Room curRoom = rooms.get(i);
            if (curRoom.players.size() == m) {
                System.out.println("Started!");
            }
            else {
                System.out.println("Waiting!");
            }
            Collections.sort(curRoom.players);
            for (Player player : curRoom.players) {
                System.out.println(player.level + " " + player.nickname);
            }
        }
    }
}
