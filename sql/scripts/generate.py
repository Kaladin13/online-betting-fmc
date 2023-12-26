import random
import string

# Function to generate dummy INSERT statements


def generate_dummy_inserts(table_name, columns):
    num_inserts = 10000  # Number of INSERT statements to generate
    insert_template = "INSERT INTO {} ({}) VALUES ({})"

    # Generate INSERT statements
    insert_statements = []
    for _ in range(num_inserts):
        values = []
        for column in columns:
            # Generate dummy values based on data type
            if 'bigint' in column[1]:
                values.append(str(random.randint(1, 100)))
            elif 'varchar' in column[1]:
                # For string columns, generating random strings of length 8
                values.append(
                    "'" + ''.join(random.choices(string.ascii_letters, k=8)) + "'")
            elif 'numeric' in column[1]:
                # Generating random numeric values
                precision, scale = map(int, column[1][8:-1].split(','))
                value = round(random.uniform(1, 100), scale)
                values.append(str(value))
            elif 'smallint' in column[1]:
                value = round(random.uniform(1, 1024))
                values.append(str(value))
            elif 'bool' in column[1]:
                values.append(str(random.choice([True, False])))
            else:
                values.append('NULL')

        insert_statement = insert_template.format(
            table_name, ', '.join([col[0] for col in columns]), ', '.join(values))
        insert_statements.append(insert_statement)

    return insert_statements


# Define the table schemas
table_schemas = {
    '"user"': [
        ('login', 'varchar(255)'),
        ('password', 'varchar(255)'),
        ('balance', 'numeric(14, 2)'),
        ('is_trusted', 'bool'),
        ('name', 'varchar(255)'),
        ('surname', 'varchar(255)'),
        ('email', 'varchar(255)'),
        ('passport', 'varchar(255)')
    ],
    'balance_tickets': [
        ('type', 'varchar(255)'),
        ('status', 'varchar(255)'),
        ('user_id', 'bigint'),
    ],
    'organisation': [
        ('name', 'varchar(255)'),
        ('logo_url', 'varchar(255)'),
        ('region', 'varchar(50)')
    ],
    'discipline': [
        ('name', 'varchar(255)'),
        ('logo_url', 'varchar(255)'),
        ('is_cybersport', 'bool')
    ],
    'team': [
        ('org_id', 'bigint'),
        ('roaster_name', 'varchar(255)'),
        ('roaster_logo_url', 'varchar(255)'),
        ('discipline_id', 'bigint')
    ],
    'tournament': [
        ('name', 'varchar(255)'),
        ('logo_url', 'varchar(255)'),
        ('discipline_id', 'bigint'),
    ],
    'match': [
        ('l_team_id', 'bigint'),
        ('r_team_id', 'bigint'),
        ('best_of', 'smallint'),
        ('tournament_id', 'bigint'),
        ('status', 'varchar(255)')
    ],
    'round': [
        ('match_id', 'bigint'),
        ('round_order', 'smallint'),
        ('status', 'varchar(255)')
    ],
    'bid_event': [
        ('round_id', 'bigint'),
        ('match_id', 'bigint'),
        ('name', 'varchar(255)'),
        ('status', 'varchar(255)'),
    ],
    'bids': [
        ('event_id', 'bigint'),
        ('name', 'varchar(255)'),
        ('rate', 'numeric(6, 3)'),
        ('status', 'varchar(255)')
    ],
    'user_bids': [
        ('bid_id', 'bigint'),
        ('user_id', 'bigint'),
        ('amount', 'numeric(14, 2)'),
        ('fixed_rate', 'numeric(6, 3)'),
        ('status', 'varchar(255)')
    ],
}

# Generate dummy INSERT statements for each table
for table_name, columns in table_schemas.items():
    insert_statements = generate_dummy_inserts(table_name, columns)
    print(f"-- Dummy INSERT statements for table '{table_name}':")
    for statement in insert_statements:
        print(statement + ';')
    print()
