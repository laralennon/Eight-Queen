import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EightQueen {
	//��ʼ״̬�б�
	public static List<State> startStates = new ArrayList<State>();
	
	//���̵���������Ҫ���õĻʺ�����
	public static final int lineNum = 6;
	
	//һ��N��N������
	public static Point[][] allPoints = new Point[lineNum][lineNum];
	
	//�ⷨ����
	public static int count = 0;
	
	public static void main(String[] args) {
		
		//��ʼ������
		for(int i=0; i<lineNum; i++){
			for(int j=0; j<lineNum; j++){
				allPoints[i][j] = new Point(i, j);
			}
		}
		
		//��ʼ����ʼ״̬�б�ÿ��State��PointList�ֱ����˵�һ�е�8�����꣬�������õ�һ��Ϊ������ʼ��
		for(int i=0; i<lineNum; i++){
			State state = new State();
			state.getPointList().add(new Point(0, i));
			state.setLineNum(0);
			startStates.add(state);
		}
		
		//���ڳ�ʼ��state�б��е�ÿ��state,���б���������
		for(State state : startStates){
			calculate(state);
		}
		System.out.println("����Ϊ��" + count); 
	}
	
	public static void calculate(State state)
	{
		Stack<State> stack = new Stack<State>();
		stack.push(state);
		while(!stack.isEmpty()){
			//��stack��ȡ��һ��״̬
			State state2 = stack.pop();
			//����Ѿ����������һ�У���������
			if(state2.getLineNum() == lineNum - 1){
				for(Point goalpoint : state2.getPointList()){
					for(int i=0; i<lineNum; i++){
						if(i!=goalpoint.getY())
							System.out.print("_ ");
						else
							System.out.print("Q ");
					}
					System.out.println(); 
				}
				System.out.println();
				count++;
				continue;
			}
			
			//����Ѱ����һ�п��Է��ûʺ��λ��
			int currentLineNum = state2.getLineNum() + 1;
			for(Point point : allPoints[currentLineNum]){
				//����õ���Է��ûʺ�
				if(isSatisfied(point, state2.getPointList()))
				{
					//����һ��state����
					State newState = new State();
					//������µ�state��pointList����Ϊǰһ�����pointList������е���ϵ�ǰ�ĵ������
					for(Point point2 : state2.getPointList()){
						newState.getPointList().add(new Point(point2.getX(), point2.getY()));
					}
					newState.getPointList().add(point);
					//�����µ�state������Ϊ��һ��
					newState.setLineNum(currentLineNum);
					//��ջ
					stack.push(newState);
				}
			}
		}
	}
	
	//�ж�һ�����Ƿ���Է��ûʺ�
	public static boolean isSatisfied(Point point, List<Point> list){
		for(Point point2 : list){
			//�����ʺ�����ͬһ�����ߡ�ֱ�ߡ�б���ϡ���������ֱ�ӱ���������һ�еĵ㣬���Կ϶��������X������ͬ�����
			if(point2.getY() == point.getY() 
					|| Math.abs(point2.getX() - point.getX()) == Math.abs(point2.getY() - point.getY()))
				return false;
		}
		return true;
	}

}
