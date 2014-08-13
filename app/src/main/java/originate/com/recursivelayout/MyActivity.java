package originate.com.recursivelayout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MyActivity extends Activity {

    // Recursive data structure
    public static class Node {
        public String name;
        public Node[] children;

        public Node (String name, Node...children) {
            this.name = name;
            this.children = children;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Node nodes1 =
        new Node("Level 1",
                new Node("Level 2",
                        new Node("Level 3",
                                new Node("Level 4", null))),
                new Node("Level 2",
                        new Node("Level 3", null)
                )
        );

        Node nodes2 =
                new Node("Level 1",
                        new Node("Level 2",
                                new Node("Level 3",
                                        new Node("Level 4",
                                                new Node("Level 5",
                                                        new Node("Level 6",
                                                                new Node("Level 7")
                                                        )
                                                )
                                        )
                                )
                        )
                );

        Node[] nodeList = new Node[] {nodes1, nodes2,nodes1, nodes2};

        LinearLayout root = (LinearLayout) findViewById(R.id.main_container);
        LayoutInflater inflator = getLayoutInflater();

        // Multiple Root:
        for (Node rootNode : nodeList)
            root.addView(createRecursiveView(inflator, root, rootNode));
    }

    private View createRecursiveView(LayoutInflater inflator, ViewGroup container, Node node) {
        View view = inflator.inflate(R.layout.recursive_layout, container, false);
        // Get a reference to the view subviews
        TextView textView = (TextView) view.findViewById(R.id.childName);
        LinearLayout childContainer = (LinearLayout) view.findViewById(R.id.children);

        // Set the contents of the view
        textView.setText(node.name);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View children = ((View) v.getParent()).findViewById(R.id.children);
                children.setVisibility(children.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
        if (node.children != null) {
            for (Node child : node.children) {
                childContainer.addView(createRecursiveView(inflator, childContainer, child));
            }
        }

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
