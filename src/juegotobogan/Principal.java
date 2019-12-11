/**
 * 
 */
package juegotobogan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * @author jonathan.arroyo
 *
 */
public class Principal {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String s;
	StringTokenizer st;

	public static void main(String[] args) {
		try {
			new Principal().iniciar();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void iniciar() throws NumberFormatException, IOException {

		int n, a = 0, b;
		int[] jugadores = new int[6];
		boolean[] fallos = new boolean[6];
		int[] tiros = new int[1001];
		TreeMap<Integer, Integer> escalera = new TreeMap<Integer, Integer>();

		TreeMap<Integer, Integer> extra = new TreeMap<Integer, Integer>();

		int ganador = 0, cur_jugadores, cur_tiros;

		n = 0;
		st = new StringTokenizer(br.readLine());

		for (tiros[n] = Integer.parseInt(st.nextToken()); tiros[n] != 0 && st.hasMoreTokens(); n++) {
			tiros[n] = Integer.parseInt(st.nextToken());
		}

//		for (tiros[n] = Integer.parseInt(br.readLine()); tiros[n] != 0; n++) {
//			tiros[n] = Integer.parseInt(br.readLine());
//		}

		escalera.clear();
		extra.clear();

		n = Integer.parseInt(br.readLine());

		for (; n > 0 && n < 6; n--) {

			while (true) {
				st = new StringTokenizer(br.readLine());

				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				if (a == 0 && b == 0) {
					break;
				}
				escalera.put(a, b);
			}

			while (true) {
				a = Integer.parseInt(br.readLine());
				if (a == 0) {
					break;
				}
				extra.put(Math.abs(a), a);

			}

			cur_jugadores = 0;
			for (int i = 0; i < 6; i++) {
				fallos[i] = false;
				jugadores[i] = 0;
			}

			cur_tiros = 0;
			while (true) {
				if (fallos[cur_jugadores]) {
					fallos[cur_jugadores] = false;
					cur_jugadores++;
					if (cur_jugadores >= n) {
						cur_jugadores = 0;
					}
					continue;
				}

				if (jugadores[cur_jugadores] + tiros[cur_tiros] > 100) {
					cur_tiros++;
					cur_jugadores++;
					continue;
				} else if (jugadores[cur_jugadores] + tiros[cur_tiros] == 100) {
					ganador = cur_jugadores + 1;
					break;
				}

				jugadores[cur_jugadores] += tiros[cur_tiros];
				cur_tiros++;

				if (escalera.containsKey(jugadores[cur_jugadores])) {
					jugadores[cur_jugadores] = escalera.get(jugadores[cur_jugadores]);
					if (jugadores[cur_jugadores] == 100) {
						ganador = cur_jugadores + 1;
						break;
					}
				}

				if (extra.containsKey(jugadores[cur_jugadores])) {
					if (extra.get(jugadores[cur_jugadores]) < 0) {
						fallos[cur_jugadores] = true;
					} else {
						cur_jugadores--;
					}
				}

				cur_jugadores++;
				if (cur_jugadores >= n) {
					cur_jugadores = 0;
				}
			}
			System.out.print("El Ganador es: ");
			System.out.print(ganador);

		}
	}
}
