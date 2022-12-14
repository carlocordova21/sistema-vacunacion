PGDMP                         z            sistema_vacunacion    14.4    14.4     	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16760    sistema_vacunacion    DATABASE     n   CREATE DATABASE sistema_vacunacion WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
 "   DROP DATABASE sistema_vacunacion;
                postgres    false            ?            1255    19171 -   check_many_doses_by_employee(bigint, integer)    FUNCTION     E  CREATE FUNCTION public.check_many_doses_by_employee(employee_id bigint, dose_number integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
dose_count integer;
BEGIN
    dose_count = (SELECT COUNT(*)
    FROM dosis 
    WHERE empleado_id = employee_id AND numero_dosis = dose_number);
    RETURN dose_count;
END;
$$;
 \   DROP FUNCTION public.check_many_doses_by_employee(employee_id bigint, dose_number integer);
       public          postgres    false            ?            1259    26353    dosis    TABLE       CREATE TABLE public.dosis (
    id bigint NOT NULL,
    fecha_dosis date NOT NULL,
    numero_dosis integer NOT NULL,
    empleado_id bigint NOT NULL,
    vacuna_id bigint NOT NULL,
    CONSTRAINT dosis_numero_dosis_check CHECK (((numero_dosis >= 1) AND (numero_dosis <= 10)))
);
    DROP TABLE public.dosis;
       public         heap    postgres    false            ?            1259    26352    dosis_id_seq    SEQUENCE     u   CREATE SEQUENCE public.dosis_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.dosis_id_seq;
       public          postgres    false    210                       0    0    dosis_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.dosis_id_seq OWNED BY public.dosis.id;
          public          postgres    false    209            ?            1259    26361 	   empleados    TABLE     ?  CREATE TABLE public.empleados (
    id bigint NOT NULL,
    activo boolean DEFAULT true NOT NULL,
    apellidos character varying(255) NOT NULL,
    cedula character varying(255) NOT NULL,
    correo character varying(255) NOT NULL,
    direccion character varying(255),
    es_vacunado boolean,
    fecha_nacimiento date,
    nombres character varying(255) NOT NULL,
    telefono character varying(255)
);
    DROP TABLE public.empleados;
       public         heap    postgres    false            ?            1259    26360    empleados_id_seq    SEQUENCE     y   CREATE SEQUENCE public.empleados_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.empleados_id_seq;
       public          postgres    false    212                       0    0    empleados_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.empleados_id_seq OWNED BY public.empleados.id;
          public          postgres    false    211            ?            1259    26371    vacunas    TABLE     d   CREATE TABLE public.vacunas (
    id bigint NOT NULL,
    nombre character varying(255) NOT NULL
);
    DROP TABLE public.vacunas;
       public         heap    postgres    false            ?            1259    26370    vacunas_id_seq    SEQUENCE     w   CREATE SEQUENCE public.vacunas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.vacunas_id_seq;
       public          postgres    false    214                       0    0    vacunas_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.vacunas_id_seq OWNED BY public.vacunas.id;
          public          postgres    false    213            g           2604    26356    dosis id    DEFAULT     d   ALTER TABLE ONLY public.dosis ALTER COLUMN id SET DEFAULT nextval('public.dosis_id_seq'::regclass);
 7   ALTER TABLE public.dosis ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209    210            i           2604    26364    empleados id    DEFAULT     l   ALTER TABLE ONLY public.empleados ALTER COLUMN id SET DEFAULT nextval('public.empleados_id_seq'::regclass);
 ;   ALTER TABLE public.empleados ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211    212            k           2604    26374 
   vacunas id    DEFAULT     h   ALTER TABLE ONLY public.vacunas ALTER COLUMN id SET DEFAULT nextval('public.vacunas_id_seq'::regclass);
 9   ALTER TABLE public.vacunas ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    213    214                      0    26353    dosis 
   TABLE DATA           V   COPY public.dosis (id, fecha_dosis, numero_dosis, empleado_id, vacuna_id) FROM stdin;
    public          postgres    false    210   c!                 0    26361 	   empleados 
   TABLE DATA           ?   COPY public.empleados (id, activo, apellidos, cedula, correo, direccion, es_vacunado, fecha_nacimiento, nombres, telefono) FROM stdin;
    public          postgres    false    212   ?!                 0    26371    vacunas 
   TABLE DATA           -   COPY public.vacunas (id, nombre) FROM stdin;
    public          postgres    false    214   ?)                  0    0    dosis_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.dosis_id_seq', 1, false);
          public          postgres    false    209                       0    0    empleados_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.empleados_id_seq', 50, true);
          public          postgres    false    211                       0    0    vacunas_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.vacunas_id_seq', 4, true);
          public          postgres    false    213            m           2606    26359    dosis dosis_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.dosis
    ADD CONSTRAINT dosis_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.dosis DROP CONSTRAINT dosis_pkey;
       public            postgres    false    210            o           2606    26369    empleados empleados_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleados_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.empleados DROP CONSTRAINT empleados_pkey;
       public            postgres    false    212            q           2606    26378 %   empleados uk7a6n7twvqwmpyxa0yvqqewvi9 
   CONSTRAINT     b   ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT uk7a6n7twvqwmpyxa0yvqqewvi9 UNIQUE (cedula);
 O   ALTER TABLE ONLY public.empleados DROP CONSTRAINT uk7a6n7twvqwmpyxa0yvqqewvi9;
       public            postgres    false    212            s           2606    26376    vacunas vacunas_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.vacunas
    ADD CONSTRAINT vacunas_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.vacunas DROP CONSTRAINT vacunas_pkey;
       public            postgres    false    214            u           2606    26384 !   dosis fkkaq76bbjbirl7u1hfy53w3ei3    FK CONSTRAINT     ?   ALTER TABLE ONLY public.dosis
    ADD CONSTRAINT fkkaq76bbjbirl7u1hfy53w3ei3 FOREIGN KEY (vacuna_id) REFERENCES public.vacunas(id);
 K   ALTER TABLE ONLY public.dosis DROP CONSTRAINT fkkaq76bbjbirl7u1hfy53w3ei3;
       public          postgres    false    3187    214    210            t           2606    26379 !   dosis fkqn6k6kt3wh6m0fogs406p9t2h    FK CONSTRAINT     ?   ALTER TABLE ONLY public.dosis
    ADD CONSTRAINT fkqn6k6kt3wh6m0fogs406p9t2h FOREIGN KEY (empleado_id) REFERENCES public.empleados(id);
 K   ALTER TABLE ONLY public.dosis DROP CONSTRAINT fkqn6k6kt3wh6m0fogs406p9t2h;
       public          postgres    false    210    3183    212                  x?????? ? ?         ?  x?}W?rܸ<?_?ۻ1?/7[?H??!)?p?\??	5??~?@??M???!P,֒??Hċ??۝!?8z7h+?,???̲BP{T???ڴ???~?U?\?u{???į[??^?SC~Jq?@????TYDR?i?eI?a7S?Jv????w?^i??pg?@??)?????Y?++/iȈ$??:iʦ{??=CQ??????/?h???????Y??????k???;wvZ?y?de?e?xu?R?+sp/?D?qs+OhC??ٍ??G>?5:?U??Y??i":?r?-N7?;??%??j?[?孚|hb?O^???!?_dqV?U???4?jB?_?;S;7*?eO^oC+<8?ힻ8?D?aTq???@??4:?Lk?ޭ?^?t????j????Y^c(??(????*?1????VYMf?{??n??F?Q??4xr?ȼ??????n3;5?~?PZ??qk0??e???W:?Lb)k???zP?dyg?gdm?A?Z??k??~??~??&g?jK?ˆ?%Q?y?TER6⠷????h;w#a?xj1?]???T;?O??N??H?"O?"Ǽʹ???"???ZOg{t??W?0????y??3/&?z?c0???l?X`????????W????O$?m?8j/?O?E??Y?i?L?,??h???|wF??O??CT??@;a??Y?#?)?DU?	O]??t???p?<k?|?????F?? X}C?y\gyZT 9??d?(????wz???7?^?g"}u??NAE? I~2?????^]o?ѭ'yC(5x????u???_gF?,"aɬ?2?>d???j`?_U/?????R?'??(o'~??ӪN?2#??y?1z?W??	m?B?)??Y?4f???Ȫ??ˢxH??]?O?????D)e*=/??Ϥ???QTU???????{?d?VQO???.)2?x? +??2?%???FwG??:??q?F?????it??B??_^??1??g^?-????Mq^??7L???E=S?????Ƀ??K?B??"?k@;?x}??9??5yK??P=e?\L??L??y?d5^%?uSoį???ux?ڄ`̙???Ϡ???*O㤨*??#?kjAP?=lh?C(?x?0QKV^,+F$U֤y?b????]PIX?œj????0Y?ȷ?8v/?W?u?Vq??????8?~?o????m?Մ??K????]?UUƐd???Y??ד?w y??XfŅ??????V?E]6uQ????l????*%??`??%??č?<qXR?????,k?`#C?]????)/X2C??'|?԰s?$?UqY?M)Ze????_??Ϙܐ??L?`?&??kE???$?'0?/?M?5^q0??,+?????\A;Q?E=??r?ڍ?<?p??5sG??v>,??Ip?	6??ݢ???????pH????K??,v'/?X?˘???̫?w???WI!M?\Կ???@\2??? *Y?.?L? ?Eg??)CU???u?w??y????1D%cn<88?n??$??]	?/k45m*????hC?P?"?}???[o???1S.	??,?b!Ҫh`?r<??HX{???:(a?:/O'0?=8s橇=?9??L?8OAL???C ?j??v???b-????k?N*???f{
??8??
??A? N?????9??kT{?ZiҠ{E
'rp6:`????ƕ?τ??0;Zje???&Kż$Hs?M\dM.??G^?;Z?q??Ŗu+\?B?<?ðE;??EQ?"`??Ȳ=Yu5@0
?%????7n??/?J ?$1,iP/?*?|`??~[?i???;??üH<??Z.y?,???H?Ż??????U?`,?zy??ýE?Ddn?U7?l??? s?2??q?$??f?r?$?? ?B?L?+??I d?<?+???z[?1?k??Rk4???Kㅜ!f??d????6????7???4?l??Y??y?p?`?p?Hk??n???W?? ?;?S%?͒?_ѧO??????         :   x?3?.(-????2?t,.)J?J?KMN?2?HˬJ-?2?????+??S??\1z\\\ ??R     